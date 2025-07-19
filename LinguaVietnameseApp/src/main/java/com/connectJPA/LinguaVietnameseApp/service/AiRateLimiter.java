package com.connectJPA.LinguaVietnameseApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AiRateLimiter {

    private final StringRedisTemplate redisTemplate;
    private static final int TIME_WINDOW_SECONDS = 10;

    public boolean allowRequest(String userId) {
        String key = "ai_limit:" + userId;
        Boolean exists = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(exists)) {
            return false;
        }
        redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(TIME_WINDOW_SECONDS));
        return true;
    }
}

