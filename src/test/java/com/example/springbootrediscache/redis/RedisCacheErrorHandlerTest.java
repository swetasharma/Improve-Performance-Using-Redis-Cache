package com.example.springbootrediscache.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RedisCacheErrorHandlerTest {
	@Test
	public void testHandleCacheGetError() {
		RedisCacheErrorHandler customCacheErrorHandler = new RedisCacheErrorHandler();
		customCacheErrorHandler.handleCacheGetError(new RuntimeException(), null, null);
	}
	
	@Test
	public void testHandleCachePutError() {
		RedisCacheErrorHandler customCacheErrorHandler = new RedisCacheErrorHandler();
		customCacheErrorHandler.handleCachePutError(new RuntimeException(), null, null, null);
	}
	
	@Test
	public void testHandleCacheEvictError() {
		RedisCacheErrorHandler customCacheErrorHandler = new RedisCacheErrorHandler();
		customCacheErrorHandler.handleCacheEvictError(new RuntimeException(), null, null);
	}
	
	@Test
	public void testHandleCacheClearError() {
		RedisCacheErrorHandler customCacheErrorHandler = new RedisCacheErrorHandler();
		customCacheErrorHandler.handleCacheClearError(new RuntimeException(), null);
	}
}
