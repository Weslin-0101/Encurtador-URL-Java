package com.encurtador.url.controllers;

import com.encurtador.url.service.URLService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "shortener/v1/url")
public class URLController {
    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "API is running";
    }

    @GetMapping
    public String getUrl(@RequestParam String shortCode) {
        return urlService.redirectURL(shortCode);
    }

    @PostMapping("/short")
    public String shortURL(@RequestBody String url) {
        return urlService.reduceURL(url);
    }
}
