package com.example.demo_db.configrure;

import com.example.demo_db.component.CustomAccessDeninedHandler;
import com.example.demo_db.component.CustomAuthenticationEntryPoint;
import com.example.demo_db.jwt.JwtFilter;
import com.example.demo_db.jwt.JwtLoginFilter;
import com.example.demo_db.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeninedHandler customAccessDeninedHandler;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();// 해당 컨피그를 가진 어슨티케이션 메니저 객체를 리턴하고, 빈의 영향도 받는다.
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpServletResponse httpServletResponse) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasicAuth -> httpBasicAuth.disable())

                .authorizeHttpRequests(authorize ->{
                authorize.requestMatchers("/","/joinadmin","/login","/password").permitAll();
                authorize.requestMatchers("/admin").hasRole("ADMIN");
//                authorize.requestMatchers("/userinfo","/userinfo/**").hasAnyRole("USER","ADMIN");
                authorize.anyRequest().authenticated();// 나머지는 인증만 되면 다 할 수 있다.
        })

                .cors(cors->cors.configurationSource(request ->{
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:3000");

                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.setExposedHeaders(List.of("Authorization"));
                    corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))

                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .addFilterBefore(new JwtFilter(this.jwtUtil), JwtLoginFilter.class)
                .addFilterAt(new JwtLoginFilter(authenticationManager(authenticationConfiguration),this.jwtUtil), UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exception->{
                    exception.authenticationEntryPoint(this.customAuthenticationEntryPoint);
                    exception.accessDeniedHandler(this.customAccessDeninedHandler);
                });
        return http.build();
    }
}
