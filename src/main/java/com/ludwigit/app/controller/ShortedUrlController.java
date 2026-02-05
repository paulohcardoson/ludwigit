package com.ludwigit.app.controller;

import com.ludwigit.app.services.ShortedUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ShortedUrlController {

    private final ShortedUrlService shortedUrlService;

    public ShortedUrlController(ShortedUrlService shortedUrlService) {
        this.shortedUrlService = shortedUrlService;
    }

    @GetMapping(path = "/{shortenUrl}")
    public ResponseEntity redirectToRealUrl(@PathVariable String shortenUrl) {
        ResponseEntity response = ResponseEntity.ok(shortedUrlService.redirectToRealUrl());
    }

    @PostMapping(path = "/create")
    public String shortUrl() {
        return "index";
    }
}
