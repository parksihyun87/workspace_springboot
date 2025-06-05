package com.example.poseexam.jwt;

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
import java.lang.management.ManagementPermission;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override//(덮,오)2가지 떄문에 오버라이드라고 생각하자.
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username= obtainUsername(request);
        String password= obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password,null);
        return this.authenticationManager.authenticate(authRequest);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();

        Collection<? extends GrantedAuthority> grantedAuthorities= userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator= grantedAuthorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String role = grantedAuthority.getAuthority();// 롤 가져오기

        Map<String,Object> responseData = new HashMap<>();
        responseData.put("username",username);
        responseData.put("role",role);
        responseData.put("result","로그인 성공"); // 나중에 보낼거에 미리 맵형태로 넣고

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData); // 맵형태는 오브젝트 맵퍼로 담아서 json으로 보냄

        // 토큰 만들기 2가지

        String access = this.jwtUtil.createToken("access",username,role,5000L);
        String refresh = this.jwtUtil.createToken("refresh",username,role,24*60*60*1000L);

        response.addHeader("Authorization", "Bearer " + access);
        response.addCookie(this.createCookie("refresh",refresh));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonMessage);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("result","로그인 실패" + failed.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData); // 맵형태는 오브젝트 맵퍼로 담아서 json으로 보냄

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonMessage);
    }

    public Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/reissue");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);// 초단위임
        return cookie;
    }
}
