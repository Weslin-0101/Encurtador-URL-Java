package com.encurtador.url.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class URL {
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
