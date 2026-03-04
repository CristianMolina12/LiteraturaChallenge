package com.practicas.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practicas.model.Libro;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosDTO(Integer count,
                       List<LibroDTO> results) {
}
