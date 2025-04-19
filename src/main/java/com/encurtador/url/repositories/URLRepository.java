package com.encurtador.url.repositories;

import com.encurtador.url.model.URL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends CrudRepository<URL, String> {
    Optional<URL> findByUrl(String url);
    Optional<URL> findByNewUrl(String newUrl);
}
