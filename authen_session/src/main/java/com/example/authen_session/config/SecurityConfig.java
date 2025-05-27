package com.example.authen_session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 암호화 위해 얘를 만들어 놓음

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {// 함수의 정의가 들어가야 함, 인증정보에서 자료빼는건 나중에
        return ((request, response, auth) -> {
            Map<String, Object> responseData= new HashMap<>();
            responseData.put("result", "로그인 성공");// 스프링 시큐리티가 전담해서 하여서 json은 자바객체로 바궈야 함

            UserDetails userDetails =(UserDetails) auth.getPrincipal();
            responseData.put("username", userDetails.getUsername());// 유저네임을 가져옴 //주요정보만 가져오려고(아디, 패스워드, 롤) 일케 함.
            responseData.put("role", userDetails.getAuthorities());// 여러개 있으면 배열형태로 문자열로 바꿔서 넣어라. 롤이 여러 개 있따면 롤을 그냥 배열정보로 해서 넣음. userDetails.getAuthorities()?

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage= objectMapper.writeValueAsString(responseData);//스트링객체로 바꿔줌. 오브젝터가 맵으로 만드는데 용이함.

            response.setStatus(200);
            response.setContentType("application/json");// 바디에 들어가있는 타입
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonMessage);
        });//틀을 만들어서 리스폰스를 채워서 넘김. 인증정보담겨있음. 로그인 성공했으니깐 그 응답을 프론트에게 보내주는게 목적, 틀이 채워진다고 함
        //빈은 리턴값이 객체로 등록 되는데 이건 함수의 정의가 그렇게 됨. 빈이라는 건 여러군데서 쓸 가능성이 있다는 염두이다.
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return ((request, response, exception) -> {
            Map<String, String> responseData= new HashMap<>();
            responseData.put("result", "로그인 실패");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage= objectMapper.writeValueAsString(responseData);// 맵객체를 제이슨 문자 객체로 바꿈
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonMessage);
        });
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/", "/join", "/login").permitAll();
                    authorize.requestMatchers("/admin").hasRole("ADMIN");
                    authorize.requestMatchers("/user", "/user/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();

                })

                .formLogin(form->
                    form.loginProcessingUrl("/login")
                            .successHandler(authenticationSuccessHandler())// 원래는 함수의 정의가 들어가는데 그냥 쓰면, 이 안에는 함수의 정의가 들어가야 함.
                            .failureHandler(authenticationFailureHandler())
                )

                .cors(cors->cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:3000");// 여러개의 출처에 대하여 허가해도 된다.

                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                    //확인
                }));

        return http.build();
    }

}

// 로그인 처리는 전적으로 시큐리티가 알아서 함., 로그인 필요한 응답객체만드는 정도만 함., 폼을 통해 로그인 함.
// 제대로 된 유저면(검증되면) 석세스 핸들러함수를 호출함. 이렇게 되면 어덴티케이션 매니저가 요청 정보를 가지고 옴.(유저네임, 패스워드)
// db에서 아디 패스워드 가져오는건 시큐리티가 못해서 우리가 구현해줌.


//                    corsConfiguration.addAllowedOrigin("http://localhost:3001");// 이런식
//                    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001")); 어레이애즈리스트하면 배열로 만들어서 형성해줌

//cors 설정