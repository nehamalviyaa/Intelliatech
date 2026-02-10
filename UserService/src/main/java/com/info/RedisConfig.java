package com.info;

import java.time.Duration;



import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;


@Configuration
@EnableCaching
public class RedisConfig {
    
	@Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory , tools.jackson.databind.ObjectMapper objectMapper) {
    	
		GenericJacksonJsonRedisSerializer serializer = new GenericJacksonJsonRedisSerializer(objectMapper);
		
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
		
		
		return RedisCacheManager.builder(factory).cacheDefaults(config).withCacheConfiguration("userList", config.entryTtl(Duration.ofMinutes(5))
				).withCacheConfiguration("users", config.entryTtl(Duration.ofMinutes(5))
						).withCacheConfiguration("userById", config.entryTtl(Duration.ofMinutes(5))).build();
    }
}
