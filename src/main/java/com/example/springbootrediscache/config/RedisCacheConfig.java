package com.example.springbootrediscache.config;

import java.time.Duration;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	private final static Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
	
	@Autowired
	private RedisProperties redisProperties;
	
	@Value("${redis.command.timeout}")
	private Duration redisCommandTimeOut;
	
	@Value("${redis.refresh.interval}")
	private long redisRefreshInterval;
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
				.commandTimeout(redisCommandTimeOut).build();
		if(Arrays.stream(environment.getActiveProfiles())
				.anyMatch(env -> ( ( env.equalsIgnoreCase("admin") || env.equalsIgnoreCase("dev") ) ))) {
			RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
			redisStandaloneConfiguration.setHostName(redisProperties.getRedisHost());
			redisStandaloneConfiguration.setPort(redisProperties.getRedisPort());
			redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getRedisPass()));
			return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
		}else{
			RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
		           .master(redisProperties.getRedisMaster());
			
			for (int i = 0; i < redisProperties.getRedisSentinelNodes().size(); i++) {
				logger.info("sentinel=" + redisProperties.getRedisSentinelNodes().get(i).split(":")[0] + ":" +Integer.valueOf(redisProperties.getRedisSentinelNodes().get(i).split(":")[1]));
				sentinelConfig.sentinel(redisProperties.getRedisSentinelNodes().get(i).split(":")[0], Integer.valueOf(redisProperties.getRedisSentinelNodes().get(i).split(":")[1]));
			}
			
		    sentinelConfig.setPassword(RedisPassword.of(redisProperties.getRedisPass()));
		    return new LettuceConnectionFactory(sentinelConfig, clientConfig);
		}
	}

    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
	
	@Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
          .entryTtl(Duration.ofHours(redisRefreshInterval))
          .disableCachingNullValues()
          .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}