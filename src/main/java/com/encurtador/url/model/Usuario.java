package com.encurtador.url.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String name;
    private String email;
    private URL url;

    public Usuario() {}

    public Usuario(
            Long id,
            String name,
            String email,
            URL url
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.url = url;
    }
}
