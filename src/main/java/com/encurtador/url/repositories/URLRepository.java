package com.encurtador.url.repositories;

import com.encurtador.url.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLRepository extends JpaRepository<URL, Long> {
    Optional<URL> findNewUrlById(Long id);
}
