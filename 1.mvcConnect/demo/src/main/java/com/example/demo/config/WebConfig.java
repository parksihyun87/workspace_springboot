package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정파일 빈 등록
@Configuration
// 인터페이스 구현함
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 요청 허락해주겠다. ** 모든 경로 괜찮다.
                .allowedOrigins("http://localhost:3000")
                // 오리진 출처: 니가 렌더링 원래 하려는데가 어디였었는지,.
                .allowCredentials(true)
                // 서버에서 허용하면 쿠키를 사용함.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 이걸 허락하겠다
                .allowedHeaders("*");
                // 모든 헤더 허용하겠다.
                //설정이 바뀐 자기 자신을 계속 리턴함.
    }
}
