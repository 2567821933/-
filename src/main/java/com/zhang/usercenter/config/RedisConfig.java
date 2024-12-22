package com.zhang.usercenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 使用redisTemplate的配置信息
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String,Object> RedisConfigClass(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringObjectRedisTemplate.setKeySerializer(RedisSerializer.string());

        stringObjectRedisTemplate.afterPropertiesSet();
        return stringObjectRedisTemplate;
    }
}
