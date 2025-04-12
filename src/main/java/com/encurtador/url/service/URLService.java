package com.encurtador.url.service;

import com.encurtador.url.model.URL;
import com.encurtador.url.repositories.URLRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String reduceURL(String url) {
        try {
            String randomPart = UUID.randomUUID().toString();
            String combined = url + randomPart;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combined.getBytes(StandardCharsets.UTF_8));

            String shortenedURL = Base64
                    .getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash);

//            URL newUrl = new URL(null, url, shortenedURL);
//            urlRepository.save(newUrl);

            return "http://myencurter.com/" + shortenedURL;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encurtar a URL", e);
        }
    }

    public String redirectURL(Long id) {
        return urlRepository.findNewUrlById(id)
                .map(URL::getUrl)
                .orElseThrow(() -> new NoSuchElementException("URL encurtada não encontrada"));
    }
}
