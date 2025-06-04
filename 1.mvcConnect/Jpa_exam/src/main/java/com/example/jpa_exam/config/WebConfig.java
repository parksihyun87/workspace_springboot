package com.example.jpa_exam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 임플리를 WebMvcConfigurer
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("PUT", "GET", "POST", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
