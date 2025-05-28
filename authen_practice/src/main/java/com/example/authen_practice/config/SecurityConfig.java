package com.example.authen_practice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return ((request, response, auth) -> {//이미 전달 된 것들을 통하여 변경하는 과정임
            //1. auth 변경하여 정보 취득하여 프론트에서 주로 사용할 데이터 가공 단
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("result", "로그인 성공");
            UserDetails userDetails = (UserDetails) auth.getPrincipal();//어센티케이션은 로그인 한 인증정보가 들어있는 객체에서 프린시펄로 사용자를 꺼냄, 로그인 성공후 사용자 정보를 담는 과정
            responseData.put("username", userDetails.getUsername());//패워는 안담음
            responseData.put("role",  userDetails.getAuthorities());// 롤 속성안에 배열형태 안의 오소리티 속성 객체: 롤이름

            // 2. 보낼준비로 자바 객체를 제이슨 형태로 변환하여 담는 단
            ObjectMapper objectMapper = new ObjectMapper();// 스프링부트에서 리스폰스 바디가 해주던 json 형태 자동변환을
            // 2단계로 수고로이 직접 변형해야 한다. 1. objectMapper로 맵에 담긴 자바 객체를 json문자열로 바꿈 (메서드.writeValueAsString으로 실행) 2. response.getwriter().write(객체) 해서 변경한 json 형태를 함수에 넣어 직접 프론트로 보내줌.
            String jsonMessage = objectMapper.writeValueAsString(responseData);

            //3. 리스폰스 변경단
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonMessage);
        });
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return ((request, response, auth) -> {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("result", "로그인 실패");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(responseData);

            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonMessage);
        });
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //암호화

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpServletResponse httpServletResponse) throws Exception {//(세필체-3,에세-2, 에세리-3)총 3가지가 필요함.
        http.csrf(csrf -> csrf.disable())//에씨 씨디
                .authorizeHttpRequests(authorize->{ // (어에리) 3가지, (리매,퍼올)2가지, (리매,해롤)2가지 (리매, 해애롤)2가지, 애리,어티 )2가지
                    authorize.requestMatchers("/","/join","/login").permitAll();
                    authorize.requestMatchers("/admin").hasRole("ADMIN");
                    authorize.requestMatchers("/user","user/**").hasAnyRole("USER","ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form->{
                    form.loginProcessingUrl("/login")
                            .successHandler(authenticationSuccessHandler())
                            .failureHandler(authenticationFailureHandler());
                })

                .logout(logout->
                        logout.logoutUrl("/logout")
                                .logoutSuccessHandler(logoutSuccessHandler())// 리퀘스트는 클라이언트가 요청에서 로그인한걸 다 묶어서 관리하는 객체임
                                .addLogoutHandler((request,response,auth)->{
                                    if(request.getSession()!=null){
                                        request.getSession().invalidate();
                                    }
                                    SecurityContextHolder.clearContext();
                                })
                                .deleteCookies("JSESSIONID")
                )
                .cors(cors->cors.configurationSource(
                        request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.addAllowedOrigin("http://localhost:3000");
                            corsConfiguration.addAllowedHeader("*");
                            corsConfiguration.addAllowedMethod("GET, POST, PUT, DELETE, OPTIONS, HEAD");
                            corsConfiguration.setAllowCredentials(true);
                            return corsConfiguration;
                        }
                ))

                .sessionManagement(session->
                        session.maximumSessions(1)
                                .maxSessionsPreventsLogin(false)
                                .expiredSessionStrategy(event -> {
                                    HttpServletResponse response= event.getResponse();
                                    response.setCharacterEncoding("UTF-8");
                                    response.getWriter().write("다른 호스트가 로그인해 현재 세션 만료됨");
                                })
                );
//                .exceptionHandling(exception->
//                        exception.authenticationEntryPoint(this.customAuthenticationEntryPoint));
        return http.build(); // 리턴 (에, 빌. ()) 3가지
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return ((request, response, auth)-> {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Logout 성공");
        });
    }
}
