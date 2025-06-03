package com.example.authen_session.jwt;

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
            throws AuthenticationException{
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password, null);
        return this.authenticationManager.authenticate(authRequest);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority>  grantedAuthorities  = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String role = grantedAuthority.getAuthority();

        Map<String, Object> responseData = new HashMap<String, Object>();
        responseData.put("role", role);
        responseData.put("result", "로그인성공");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData);
// 토큰 정보도 넣을 거임.
        String access = this.jwtUtil.createToken("access", username, role, 5000L);
        String refresh = this.jwtUtil.createToken("refresh", username, role, 60*60*24*1000L);
        //* 억세스와 리프레시로 두개 만든다. 리프레시 토큰은 쿠키에 넣는다.
        // * 리프레시는 쿠키에, 엑세스는 헤더에 담는다. 쿠키가 오히려 안정성이 높아진다.

        response.addHeader("Authorization", "Bearer " + access);
        response.addCookie(this.createCookie("refresh", refresh));// *이렇게 만듬. 엑세스는 헤더에, 리프레시는 쿠키에.

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonMessage);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException{
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("error", "로그인실패");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonMessage);
    }

    private Cookie createCookie(String key, String value) {//브라우저에 저장되고 포함되어 올텐데 요청하는 유알엘을 포함. 이 유알엘 이하로 시작하는 모든 경로
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);// 쿠키를 포함해서 보낼 수 있지만, 코드에서 가져다 읽어서 쓸수는 없게 한다.(리액트 이런데에서 코드내에서는 읽지 못하게 한다. 자바스크립트의 코드 공격에 방어하는 의미
        cookie.setMaxAge(60*60*24);// 여기도 자체로 유효기간 정할 수 잇는데 초단위임.
        return cookie;
    }
}
