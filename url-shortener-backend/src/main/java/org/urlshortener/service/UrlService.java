package org.urlshortener.service;

public interface UrlService {

    String shortenUrl(String longUrl);

    String getLongUrl(String shortUrl);
}