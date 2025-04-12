package com.encurtador.url.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String newUrl;

    public URL() {}

    public URL(Long id, String url, String newUrl) {
        this.id = id;
        this.url = url;
        this.newUrl = newUrl;
    }
}
