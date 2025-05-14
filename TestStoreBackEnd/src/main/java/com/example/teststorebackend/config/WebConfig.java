package com.example.teststorebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //환경 설정 클래스
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").
                allowedOrigins("http://localhost:3000").
                allowCredentials(true).
                allowedHeaders("*").
                allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
