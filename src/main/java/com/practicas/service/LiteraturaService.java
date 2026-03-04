package com.practicas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicas.dto.DatosDTO;
import com.practicas.dto.LibroDTO;
import org.springframework.stereotype.Service;

@Service
public class LiteraturaService {

    private final ConsumoApi consumoApi;
    private final ObjectMapper mapper = new ObjectMapper();

    public LiteraturaService(ConsumoApi consumoApi) {
        this.consumoApi = consumoApi;
    }

    public void buscarLibro(String titulo) {

        String url = "https://gutendex.com/books?search=" + titulo;

        String json = consumoApi.obtenerDatos(url);

        try {
            DatosDTO datos = mapper.readValue(json, DatosDTO.class);

            if (!datos.results().isEmpty()) {
                LibroDTO libro = datos.results().get(0);
                System.out.println("Libro encontrado: " + libro.titulo());
            } else {
                System.out.println("No se encontraron libros.");
            }

        } catch (Exception e) {
            System.out.println("Error al convertir JSON");
        }
    }
}