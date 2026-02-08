package org.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshortener.model.UrlMapping;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortUrl(String shortUrl);

    Optional<UrlMapping> findByLongUrl(String longUrl);
}