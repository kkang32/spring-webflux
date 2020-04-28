package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class WebConfig{// extends CachingConfigurerSupport{

	
	/*
	
	@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("127.0.0.1", 6379);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
	
	*/
	
	@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
	
	
//	@Bean
//	@Override
//	public CacheManager cacheManager() {
//		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
//				.fromConnectionFactory(new LettuceConnectionFactory());
//		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
//				.serializeValuesWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(new GenericJackson2JsonRedisSerializer()))
//				.prefixKeysWith("imhere:");
//		builder.cacheDefaults(configuration);
//		return builder.build();
//	}
//	
//	@Bean
//	public LettuceConnectionFactory lettuceConnFactory() {
//		return new LettuceConnectionFactory() {{
//			setHostName("127.0.0.1");
//			setPort(6379);
//		}};
//	}


}
