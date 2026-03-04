package com.practicas.repository;

import com.practicas.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrosRepository  extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
}
