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

    @GetMapping
    public String getUrl(@RequestParam Long id) {
        return urlService.redirectURL(id);
    }

    @PostMapping("/short")
    public String shortURL(@RequestBody String url) {
        return urlService.reduceURL(url);
    }
}
