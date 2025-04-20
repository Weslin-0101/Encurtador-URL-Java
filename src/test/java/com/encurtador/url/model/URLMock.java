package com.encurtador.url.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class URLMock {

    public URL mockURL() {
        return new URL(
                1L,
                "https://www.google.com",
                "http://myencurter.com/abc123"
        );
    }
}
