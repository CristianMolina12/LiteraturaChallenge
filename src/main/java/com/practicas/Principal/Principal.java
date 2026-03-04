package com.practicas.Principal;

import com.practicas.model.Datos;
import com.practicas.model.DatosLibro;
import com.practicas.model.Libro;
import com.practicas.repository.LibrosRepository;
import com.practicas.service.ConsumoApi;
import com.practicas.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repository;

    public Principal(LibrosRepository repository) {
        this.repository = repository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título.
                    
                    2 - Listar libros registrados.
                    
                    3 - Listar autores registrados.
                    
                    4 - Listar autores vivos en un determinado año.
                    
                    5 - Listar libros por idioma.
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println("Por favor, escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var nombreFormateado = nombreLibro.toLowerCase().replace(" ", "+");
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreFormateado);

        Datos datos = conversor.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibroWeb() {

    }
}
