package com.example.poseexam.controller;

import com.example.poseexam.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String refresh_token = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refresh_token = cookie.getValue();
                break;
            }
        }

        //1.쿠키 null
        if(refresh_token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 null");
        }

        // 2.refresh 토큰 만료 여부
        try{
            this.jwtUtil.isExpired(refresh_token);
        } catch (ExpiredJwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("만료된 토큰입니다.");
        }
        // 3. 카테고리 확인
        String category= this.jwtUtil.getCategory(refresh_token);
        if(!category.equals("refresh")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        //억세스 다시 만들기(리프레시에서 일부 정보 발췌)
        String username= this.jwtUtil.getUsername(refresh_token);
        String role= this.jwtUtil.getRole(refresh_token);

        String access= this.jwtUtil.createToken("access",username,role,5000L);

        response.addHeader("Authorization","Bearer "+access);

        return ResponseEntity.status(HttpStatus.OK).body("토큰 발급 성공");
    }


}
