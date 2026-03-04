package com.practicas.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(String nombre,
                       Integer fechaNacimiento,
                       Integer fechaFallecimiento) {
}
