package com.example.springbootrediscache.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisProperties {
	private Integer redisPort;
	private String redisHost;
	private String redisPass;
	private String redisMaster;
	private List<String> redisSentinelNodes;

    public RedisProperties(
		@Value("#{new Integer('${spring.redis.port}')}") int redisPort, 
		@Value("${spring.redis.host}") String redisHost,
		@Value("${spring.redis.password}") String redisPass,
		@Value("${spring.redis.sentinel.master}") String redisMaster,
		@Value("#{'${spring.redis.sentinel.nodes}'.split(',')}") List<String> redisSentinelNodes) 
		{
	        this.redisPort = redisPort;
	        this.redisHost = redisHost;
	        this.redisPass = redisPass;
	        this.redisMaster = redisMaster;
	        this.redisSentinelNodes = redisSentinelNodes;
	    }

	public String getRedisMaster() {
		return redisMaster;
	}

	public Integer getRedisPort() {
		return redisPort;
	}
	
	public String getRedisHost() {
		return redisHost;
	}
	
	public String getRedisPass() {
		return redisPass;
	}

	public List<String> getRedisSentinelNodes() {
		return redisSentinelNodes;
	}	
}