package com.practicas.repository;

import com.practicas.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository  extends JpaRepository<Libro, Long> {
}
