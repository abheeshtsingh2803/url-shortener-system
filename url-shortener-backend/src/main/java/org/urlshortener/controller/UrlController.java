package org.urlshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshortener.dto.UrlRequest;
import org.urlshortener.dto.UrlResponse;
import org.urlshortener.service.UrlService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public UrlResponse shortenUrl(@RequestBody UrlRequest request) {
        String shortCode = urlService.shortenUrl(request.getLongUrl());
        return new UrlResponse("http://localhost:8080/api/v1/" + shortCode);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(
            @PathVariable("shortUrl") String shortUrl
    ) {
        String longUrl = urlService.getLongUrl(shortUrl);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}