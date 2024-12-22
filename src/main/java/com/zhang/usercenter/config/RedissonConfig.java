package com.zhang.usercenter.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Redisson 配置
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig {

    private String port;
    private String host;
    private String password;



    // 下次记得考虑密码
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String format = String.format("redis://%s:%s",  host,port);

        config.useSingleServer().setAddress(format).setPassword("123456");
        return Redisson.create(config);
    }
}
