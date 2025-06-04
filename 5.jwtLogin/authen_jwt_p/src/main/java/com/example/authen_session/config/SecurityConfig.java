package com.example.authen_session.config;

import com.example.authen_session.component.CustomAccessDeniedHandler;
import com.example.authen_session.component.CustomAuthenticationEntryPoint;
import com.example.authen_session.jwt.JwtFilter;
import com.example.authen_session.jwt.JwtLoginFilter;
import com.example.authen_session.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;// *어덴 환경 객체는 기본적으로 빈으로 등록되어 있는, 시큐리티 제공 항목
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtUtil jwtUtil;

    @Bean// @Configuration 클래스 안의 @Bean 메서드이므로, 반환된 AuthenticationManager도 빈으로 등록됨
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 암호화 위해 얘를 만들어 놓음
    // 석세스, 패일려, 로그아웃 지움

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpServletResponse httpServletResponse) throws Exception {
        http.csrf(csrf -> csrf.disable())//*csrf를 끈다, 완전 다른방식이므로
                .formLogin(formLogin -> formLogin.disable())// *세션 방식이라서 디저블
                .httpBasic(httpBasicAuth -> httpBasicAuth.disable())//*기본 로그인 방식 이라는데 이것도 디저블

                    .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/", "/join", "/login","/csrf-token").permitAll();
                    authorize.requestMatchers("/admin").hasRole("ADMIN");
//                    authorize.requestMatchers("/user", "/user/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();

                })
                //* 로그인, 로그아웃 삭제

                .cors(cors->cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:3000");// 여러개의 출처에 대하여 허가해도 된다.

                    corsConfiguration.addAllowedHeader("*");// 클라이언ㅡ가 요청을 보낼때 보낼수 잇는 헤더
                    corsConfiguration.setExposedHeaders(List.of("Authorization"));// *추가된 부분 백엔드에서 보내는 헤더의 어더라이제이션을 프론트 자바스크립트에서 읽도록 허용해주겠다는 뜻, 하나만이면 addexpose
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));//* 둘다 가능 Arrays.asList
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                    //확인
                }))
                //*세션매니지먼트 기존거 삭제
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)//* 세션을 만드는 데 있어서 정책이 스테이트리스, 인증 정보를 저장해놓지 않겠다는 거고 즉 jwt를 사용하겠다는 뜻.
                )

                .addFilterBefore(new JwtFilter(this.jwtUtil), JwtLoginFilter.class)// 비포이므로 제덥티필터를, 로긴필터 전에 꼽겠다는 뜻.
                .addFilterAt(new JwtLoginFilter(authenticationManager(authenticationConfiguration), this.jwtUtil), UsernamePasswordAuthenticationFilter.class)//필터 엣은 그자리에 넣는다 유저 네임패스워드 자리에 덮어씌우겠다.로긴 필터는 주입이 필요함. 어덴 매니저랑 유틸 생성자 필요
                // 순서는 제덥티 필터, 로긴 필터가 된다.

                .exceptionHandling(exception->{
                        exception.authenticationEntryPoint(this.customAuthenticationEntryPoint);// *객체가 필요, 어드민의 잘못된 접근을 먹어버림
                        exception.accessDeniedHandler(this.customAccessDeniedHandler);// * 이렇게 넣어서 핸들러를 등록함.
                        }
                        );

        return http.build();
    }


}

// 로그인 처리는 전적으로 시큐리티가 알아서 함., 로그인 필요한 응답객체만드는 정도만 함., 폼을 통해 로그인 함.
// 제대로 된 유저면(검증되면) 석세스 핸들러함수를 호출함. 이렇게 되면 어덴티케이션 매니저가 요청 정보를 가지고 옴.(유저네임, 패스워드)
// db에서 아디 패스워드 가져오는건 시큐리티가 못해서 우리가 구현해줌.


//                    corsConfiguration.addAllowedOrigin("http://localhost:3001");// 이런식
//                    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001")); 어레이애즈리스트하면 배열로 만들어서 형성해줌

//cors 설정