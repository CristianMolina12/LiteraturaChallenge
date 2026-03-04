package com.practicas.Principal;

import com.practicas.model.*;
import com.practicas.repository.AutorRepository;
import com.practicas.repository.LibrosRepository;
import com.practicas.service.ConsumoApi;
import com.practicas.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private LibrosRepository repository;
    private AutorRepository autorRepository;

    public Principal(LibrosRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;

        while (opcion != 0) {

            var menu = """
                    1 - Buscar libro por título
                    2 - Buscar libros guardados
                    3 - Mostrar autores
                    4 - Mostrar libros por idioma
                    5 - Mostrar autores vivos en una años determinado
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarLibroPorIdioma();
                    break;
                case 5:
                    listarAutoresVivos();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarAutoresVivos() {

        System.out.println("Ingresa el año:");
        var anio = teclado.nextInt();
        teclado.nextLine();

        var autores = autorRepository.autoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
            return;
        }

        autores.forEach(autor ->
                System.out.println(
                        "Autor: " + autor.getNombre() +
                                " Nacimiento: " + autor.getFechaNacimiento() +
                                " Fallecimiento: " + autor.getFechaFallecimiento()
                )
        );
    }

    private void listarLibroPorIdioma() {
        System.out.println("Escribe el idioma (ejemplo: en, es, fr):");
        var idioma = teclado.nextLine();
        var libros = repository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma");
            return;
        }

        libros.forEach(libro ->
                System.out.println("Titulo: " + libro.getTitulo() +
                        " Autor: " + libro.getAutor().getNombre() +
                        " Descargas: " + libro.getNumeroDescargas()));
    }

    private void listarAutores() {
        var autores= autorRepository.findAll();

        if (autores.isEmpty()){
            System.out.println("No hay autores registrados");
            return;
        }

        autores.forEach(autor ->
                System.out.println(" Autor: " + autor.getNombre()+
                        " Nacimiento: " + autor.getFechaNacimiento()+
                        " Fallecimiento: " + autor.getFechaFallecimiento()));
    }

    private void listarLibros() {

        var libros = repository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        libros.forEach(libro ->
                System.out.println(
                        "Título: " + libro.getTitulo() +
                                " | Autor: " + libro.getAutor().getNombre() +
                                " | Idioma: " + libro.getIdioma() +
                                " | Descargas: " + libro.getNumeroDescargas()
                )
        );
    }

    private void buscarLibroWeb() {

        System.out.println("Por favor, escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));

        if (json == null || json.isBlank()) {
            System.out.println("No se pudo obtener información desde la API.");
            return;
        }

        var datos = convierteDatos.obtenerDatos(json, Datos.class);

        if (datos == null || datos.resultados() == null || datos.resultados().isEmpty()) {
            System.out.println("No se encontraron libros con ese nombre.");
            return;
        }

        var datosLibro = datos.resultados().get(0);

        if (datosLibro.autor() == null || datosLibro.autor().isEmpty()) {
            System.out.println("El libro no tiene autor registrado.");
            return;
        }

        var datosAutor = datosLibro.autor().get(0);

        String idioma = (datosLibro.idiomas() == null || datosLibro.idiomas().isEmpty())
                ? "desconocido"
                : datosLibro.idiomas().get(0);

        Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor(
                            datosAutor.nombre(),
                            datosAutor.fechaNacimiento(),
                            datosAutor.fechaFallecimiento()
                    );
                    return autorRepository.save(nuevoAutor);
                });

        Optional<Libro> libroExistente = repository.findByTitulo(datosLibro.titulo());

        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está registrado.");
            return;
        }

        Libro libro = new Libro(
                datosLibro.titulo(),
                idioma,
                datosLibro.numeroDeDescargas(),
                autor
        );

        repository.save(libro);

        System.out.println("Libro guardado exitosamente.");
    }
}