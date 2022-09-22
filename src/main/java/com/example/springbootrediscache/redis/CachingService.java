package com.example.springbootrediscache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.example.springbootrediscache.pojo.ClearCacheResponse;

@Service
public class CachingService {
	
	private final static Logger logger = LoggerFactory.getLogger(CachingService.class);

	@Autowired
	private CacheManager cacheManager;
	
	public ClearCacheResponse evictAllCaches() {
		logger.info("Start - Clearing of cache");
		cacheManager.getCacheNames().parallelStream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			
		ClearCacheResponse clearCacheResponse = new ClearCacheResponse();
		clearCacheResponse.setMessage("ok");
		logger.info("End - Clearing of cache");
		return clearCacheResponse;
	}
}
