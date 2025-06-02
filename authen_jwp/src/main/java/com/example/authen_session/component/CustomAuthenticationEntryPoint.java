package com.example.authen_session.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {// io 익셉션: 인증이 없거나 만료될때 익셉션으로 들어감. 리스폰스에 담아서 넘겨주는 역할
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("error","Unauthorized");
        responseData.put("message","먼저로그인하고 시도하세요");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonMessage);
    }
}
