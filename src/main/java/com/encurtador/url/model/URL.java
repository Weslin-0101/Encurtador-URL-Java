package com.encurtador.url.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String newUrl;

    public URL() {}

    public URL(Long id, String url, String newUrl) {
        this.id = id;
        this.url = url;
        this.newUrl = newUrl;
    }
}
