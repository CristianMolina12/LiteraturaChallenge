package com.practicas.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(String titulo,
                       List<AutorDTO> autor,
                       List<String>lenguajes,
                       Integer descargas) {
}
