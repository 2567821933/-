package com.zhang.usercenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration //设置配置
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //拦截所有请求
                .allowedOriginPatterns("*") //允许任何域名使用
                .allowCredentials(true) //允许请求信息携带cookie，后面单词是认证。
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //允许任何方法（post、get等）
                .maxAge(3600);
    }
}
