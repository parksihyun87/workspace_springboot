package com.example.authen_session.config;

import com.example.authen_session.component.CustomAuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


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

            CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            responseData.put("csrf-token", token.getToken());// 한번 토큰을 줬으면 다른 값으로 토큰을 줌.

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpServletResponse httpServletResponse) throws Exception {
//        http.csrf(csrf -> csrf.disable())//csrf를 비활성화 해서 보안설정을 다르게 한다.
                http.authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/", "/join", "/login","/csrf-token").permitAll();
                    authorize.requestMatchers("/admin").hasRole("ADMIN");
                    authorize.requestMatchers("/user", "/user/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();

                })

                .formLogin(form->
                    form.loginProcessingUrl("/login")
                            .successHandler(authenticationSuccessHandler())// 원래는 함수의 정의가 들어가는데 그냥 쓰면, 이 안에는 함수의 정의가 들어가야 함.
                            .failureHandler(authenticationFailureHandler())
                )

                .logout(logout->
                    logout.logoutUrl("/logout")
                            .logoutSuccessHandler(logoutSuccessHandler())// 로그아웃이 끝나고(밑에 일들이 다 끝나고) 그 결과를 응답객체로 보내주는 작업을 작성함. // 로그아웃 과정에서 서버가 해야할 일을 정해 놓는 과정이다.
                            .addLogoutHandler((request, response, authentication) -> {
                                if(request.getSession() != null){// 리퀘스트의 세션정보가 있으면(널이 아니면)
                                    request.getSession().invalidate();// 서버에서 세션 정보를 무효화 시켜라. 이게 로그아웃인가보네.. 유저의 모든 내용을 가진 전역적 정보.
                                }
                                SecurityContextHolder.clearContext(); // aaa 111 주정보를 전역정보로 저장해놓고, 다른 bbb 들어와도 저장해놓고, 다시 aaa 가 요청할때 임시로 aaa의 요청을 임시로 저장해 놓는게 있음. 컨텍스트는 지금 막 처리하고 있는 정보(이번 요청에 대한 정보), 스레드 로컬에 저장함. 컴터는 바구니 가득 인형 눈알붙이기 할라고 전부다 인형 복사해왔는데 그것도 삭제
                            })                           .deleteCookies("JSESSIONID")// 클라이언트 부라우저에게 제이세션 아이디를 삭제해라라고 (쿠키에 저장한거를) 명령하는 것.

                )

                .cors(cors->cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:3000");// 여러개의 출처에 대하여 허가해도 된다.

                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                    //확인
                }))

                .sessionManagement(session->// 중복로그인에 대한 설정.
                        session.maximumSessions(1)// 세션으로 내가 한번에 만들수 있는 계정을 하나로만 제한함.(내가 로그인 되어있으면 다른 곳에서 로그인하지 못하도록 함) //다른데에서 해서 만료된 것들을 적음. 이벤트 객체는 두번째에서 로그인하려고 하는 이벤트가 발생하면, 이벤트에서 발생할 리스폰스를 가져옴.
                                .maxSessionsPreventsLogin(false)//폴스하면 기존꺼없애고 로그인, 트루면 이미 되어있어서 못들간다
                                .expiredSessionStrategy(event->{
                                    HttpServletResponse response =event.getResponse();
                                            response.setCharacterEncoding("UTF-8");
                                            response.getWriter().write("다른 호스트에서 로그인하여 현재 세션이 만료되었습니다.");// 다른 누군가가 접속하면 나한테 들어오는 출력
                                })
                        )
                .exceptionHandling(exception->
                        exception.authenticationEntryPoint(this.customAuthenticationEntryPoint)// 객체가 필요
                        );

        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return ((request, response, authentication) -> {
            response.setStatus(200);
            response. setCharacterEncoding("UTF-8");// 없으면 기본은 아스키 코드
            response.getWriter().write("Logout 성공");//한글이 들어가면 위의 유니 코드 셋 코딩이 들어감
        });
    }

}

// 로그인 처리는 전적으로 시큐리티가 알아서 함., 로그인 필요한 응답객체만드는 정도만 함., 폼을 통해 로그인 함.
// 제대로 된 유저면(검증되면) 석세스 핸들러함수를 호출함. 이렇게 되면 어덴티케이션 매니저가 요청 정보를 가지고 옴.(유저네임, 패스워드)
// db에서 아디 패스워드 가져오는건 시큐리티가 못해서 우리가 구현해줌.


//                    corsConfiguration.addAllowedOrigin("http://localhost:3001");// 이런식
//                    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001")); 어레이애즈리스트하면 배열로 만들어서 형성해줌

//cors 설정