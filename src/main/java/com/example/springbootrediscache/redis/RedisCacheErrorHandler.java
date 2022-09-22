package com.example.springbootrediscache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class RedisCacheErrorHandler implements CacheErrorHandler{
	
	private final static Logger logger = LoggerFactory.getLogger(RedisCacheErrorHandler.class);
	
	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		logger.error(exception.getMessage(), exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		logger.error(exception.getMessage(), exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		logger.error(exception.getMessage(), exception);
		
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		logger.error(exception.getMessage(), exception);
	}

}