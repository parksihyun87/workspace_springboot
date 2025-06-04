package com.example.demo_db.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{//( abstract, ioexception, servlerException)3가지 뺌
        String username = obtainUsername(request);// (옵, 유저네임, 패스워드) 옵 1으로 유,패2가지 가져옴
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password, null);// 세션이 자동으로 하던일 수동으로
        return this.authenticationManager.authenticate(authRequest);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 겟 프린시펄 리턴하는 자료형
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();// 결과에서 주요 인증정보 추출
        String username = userDetails.getUsername();// 유저네임 추출

        Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String role= grantedAuthority.getAuthority();

        Map<String,Object> responseData = new HashMap<>();
        responseData.put("username",username);
        responseData.put("role",role);
        responseData.put("result","로그인 성공");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage= objectMapper.writeValueAsString(responseData);

        // 성공시 토큰 만드는 2가지가 중요함.
        String access = this.jwtUtil.createToken("access",username,role,5000L);//(유저네임, 롤, (유효기간,L-2가지))3가지를 챙겨서 넣어줘야 함
        String refresh = this.jwtUtil.createToken("refresh",username,role,24*60*60*1000L);//(유저네임, 롤, (유효기간,L-2가지))3가지를 챙겨서 넣어줘야 함

        response.addHeader("Authorization","Bearer "+access);
        response.addCookie(this.createCookie("refresh", refresh));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonMessage);// 여기 바디에는 롤 정보만 넣어서 보낸다. 중요한것은 헤더에 토큰을 더했다는것
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("result", "로그인실패:" + failed.getMessage());// 로그인 한정 잘못된건 다 여기로 간다.

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage= objectMapper.writeValueAsString(responseData);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonMessage);
    }

    public Cookie createCookie(String key,String value){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/reissue");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);
        return cookie;
    }
}
