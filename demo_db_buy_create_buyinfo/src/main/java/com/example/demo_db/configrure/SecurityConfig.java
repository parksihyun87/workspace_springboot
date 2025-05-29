package com.example.demo_db.configrure;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.cors.CorsConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpServletResponse httpServletResponse) throws Exception {
//        http.csrf(csrf -> csrf.disable());//에씨 씨디
        http.authorizeHttpRequests(authorize ->{
                authorize.requestMatchers("/","/joinadmin","/login","/csrf-token").permitAll();
                authorize.requestMatchers("/admin").hasRole("ADMIN");
                authorize.requestMatchers("/user","/user/**").hasAnyRole("USER","ADMIN");
                authorize.anyRequest().authenticated();
        })
                .formLogin((form)->
                        form.loginProcessingUrl("/login")
                                .successHandler(authenticationSuccessHandler())
                                .failureHandler(authenticationFailureHandler())
                )

                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessHandler(logoutSuccessHandler())
                                .addLogoutHandler((request,response,auth)->{
                                    if(request.getSession()!= null){
                                        request.getSession().invalidate();
                                    }
                                    SecurityContextHolder.clearContext();
                                })
                                .deleteCookies("JSESSIONID")
                )

                .cors(cors->cors.configurationSource(request ->{
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:3000");

                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));//GET, POST, PUT, DELETE, OPTIONS 이렇게 쓰려면 메서드도 (세얼메)3가지이고 (리,오)2가지 하고 써야 함.
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))

                .sessionManagement(session->
                        session.maximumSessions(1)
                                .maxSessionsPreventsLogin(false)
                                .expiredSessionStrategy(event -> {
                                    HttpServletResponse response = (HttpServletResponse) event.getResponse();
                                    response.setCharacterEncoding("UTF-8");
                                    response.getWriter().write("다른 호스트 로그인 세션 만료");
                                })
                        );
// 익셉션 엔트리 포인트 부재함
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return ((request, response, authentication) -> {

            Map<String,Object> responseData = new HashMap<>();
            responseData.put("result","로그인 성공");

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();// 인증된 유저의 주요 정보 가져오기
            responseData.put("username",userDetails.getUsername());
            responseData.put("roles",userDetails.getAuthorities());// 권한정보(롤),여러개면 배열임

            CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            responseData.put("csrf-token",token.getToken());// 다른 토큰 받기

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(responseData);// 맵 객체를 스트링 객체로 바꿔줘서 json에 보낼 형식으로 만들어줌

            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonMessage);
        });
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return ((request, response, exception) -> {
            Map<String,Object> responseData = new HashMap<>();
            responseData.put("result","로그인 실패");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(responseData);

            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonMessage);
        });
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return((request, response, authentication) -> {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("로그아웃 성공");
        });
    }
}
