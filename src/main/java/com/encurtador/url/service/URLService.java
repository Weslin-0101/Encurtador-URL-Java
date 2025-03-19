package com.encurtador.url.service;

import com.encurtador.url.model.URL;
import com.encurtador.url.repositories.URLRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String reduceURL(String url) {
        String shortenedURL = Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(url.getBytes(StandardCharsets.UTF_8))
                .substring(0, 8);

        URL newUrl = new URL(null, url, shortenedURL);
        urlRepository.save(newUrl);

        return "http://myencurter.com/" + shortenedURL;
    }

    public String redirectURL(Long id) {
        return urlRepository.findNewUrlById(id)
                .map(URL::getUrl)
                .orElseThrow(() -> new NoSuchElementException("URL encurtada não encontrada"));
    }
}
