package com.encurtador.url.service;

import com.encurtador.url.model.URL;
import com.encurtador.url.repositories.URLRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String reduceURL(String url) {
        try {
            Optional<URL> existingUrl = urlRepository.findByUrl(url);
            if (existingUrl.isPresent()) {
                return "http://myencurter.com/" + existingUrl.get().getNewUrl();
            }

            String shortCode = generateShortCode();

            while (urlRepository.findByNewUrl(shortCode).isPresent()) {
                shortCode = generateShortCode();
            }

            URL newUrl = new URL(null, url, shortCode);
            urlRepository.save(newUrl);

            return "http://myencurter.com/" + shortCode;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao encurtar a URL", e);
        }
    }

    private String generateShortCode() {
        String uuid = UUID.randomUUID().toString();

        byte[] bytes = uuid.substring(0, 6).getBytes(StandardCharsets.UTF_8);
        String shortCode = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        return shortCode.substring(0, Math.min(shortCode.length(), 8));
    }

    public String redirectURL(String shortCode) {
        return urlRepository.findByNewUrl(shortCode)
                .map(URL::getUrl)
                .orElseThrow(() -> new NoSuchElementException("URL encurtada não encontrada"));
    }
}
