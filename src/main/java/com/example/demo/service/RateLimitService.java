package com.example.demo.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RateLimitService {
	
	
	private final StringRedisTemplate redisTemplate;
	
	@Value("${rate.limit.max.requests:50}")
	private int homeMaxRequests;
	
	@Value("${rate.limit.max.requests:10}")
	private int messageMaxRequests;
	
	@Value("${rate.limit.max.requests:10}")
	private int newListingMaxRequests;
	
	@Value("${rate.limit.window.seconds:60}")
	private int windowInSeconds;

	public RateLimitService(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public boolean isRateLimited(String key, String feature) {
		int maxRequests = getRequestsForFeature(feature);
		String redisKey = "rate.limit:" + key;
		String currentRequests = redisTemplate.opsForValue().get(redisKey);
		
		if(StringUtils.isEmpty(currentRequests)) {
			redisTemplate.opsForValue().set(redisKey, "1", Duration.ofSeconds(windowInSeconds));
			return false;
		}
		
		int requestCount = Integer.parseInt(currentRequests);
		if(requestCount > maxRequests) {
			return true;
		} else {
			redisTemplate.opsForValue().increment(redisKey);
			return false;
		}
	}
	
	private int getRequestsForFeature(String feature) {
        return switch (feature) {
            case "home" -> homeMaxRequests;
            case "message" -> messageMaxRequests;
            case "newListing" -> newListingMaxRequests;
            default -> 10;
        };
	}

}
