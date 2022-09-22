package com.example.springbootrediscache.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.springbootrediscache.pojo.ClearCacheResponse;

@ExtendWith(SpringExtension.class)
public class CachingServiceTest {
	@InjectMocks
	private CachingService testCachingService;

	@Mock
	private CacheManager cacheManager;
	
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testEvictAllCaches() {
		ClearCacheResponse clearCacheResponse = testCachingService.evictAllCaches();
		assertEquals("ok", clearCacheResponse.getMessage());
	}
}
