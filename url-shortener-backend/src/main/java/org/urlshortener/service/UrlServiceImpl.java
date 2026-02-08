package org.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.urlshortener.exception.UrlNotFoundException;
import org.urlshortener.model.UrlMapping;
import org.urlshortener.repository.UrlRepository;
import org.urlshortener.util.Base62Encoder;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final Base62Encoder encoder;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String shortenUrl(String longUrl) {

        return repository.findByLongUrl(longUrl)
                .map(UrlMapping::getShortUrl)
                .orElseGet(() -> {
                    UrlMapping mapping = new UrlMapping();
                    mapping.setLongUrl(longUrl);
                    repository.save(mapping);

                    String shortCode = encoder.encode(mapping.getId());
                    mapping.setShortUrl(shortCode);
                    repository.save(mapping);

                    return shortCode;
                });
    }

    @Override
    public String getLongUrl(String shortUrl) {

        String cached = redisTemplate.opsForValue().get(shortUrl);
        if (cached != null) {
            return cached;
        }

        UrlMapping mapping = repository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

        redisTemplate.opsForValue().set(shortUrl, mapping.getLongUrl());
        return mapping.getLongUrl();
    }
}