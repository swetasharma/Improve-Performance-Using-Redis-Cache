package com.example.springbootrediscache.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;

import com.example.springbootrediscache.redis.RedisCacheErrorHandler;

@Configuration
public class CachingConfiguration extends CachingConfigurerSupport{

	@Override
	public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }
}