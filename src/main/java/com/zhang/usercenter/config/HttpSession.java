//package com.zhang.usercenter.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.redis.config.ConfigureRedisAction;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//// 设置session过期时间
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
//public class HttpSession {
//    @Bean
//    public static ConfigureRedisAction configrueRedisActon() {
//        return ConfigureRedisAction.NO_OP;
//    }
//}
