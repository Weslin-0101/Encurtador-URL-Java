package com.encurtador.url.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@RedisHash("url")
public class URL {
    @Id
    private Long id;

    @Indexed
    private String url;

    @Indexed
    private String newUrl;

    private Long timeToLive = 3600L;

    public URL() {}

    public URL(Long id, String url, String newUrl) {
        this.id = id;
        this.url = url;
        this.newUrl = newUrl;
    }
}
