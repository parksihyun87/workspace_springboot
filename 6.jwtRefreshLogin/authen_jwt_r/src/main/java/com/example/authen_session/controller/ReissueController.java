package com.example.authen_session.controller;

import com.example.authen_session.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {// 리프레시 토큰으로 억세스를 요청할때 필요한 클래스
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refresh_token = null;
        Cookie[] cookies = request.getCookies();// 쿠키가 여러개일 수 있어서 일케 가져옴
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){// 쿠키의 키가 겟네임에의해 리턴됨.
                refresh_token = cookie.getValue();
                break;
            }
        }
        if(refresh_token == null){// 안담기면 발급 안함.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 null");
        }

        try {
            jwtUtil.isExpired(refresh_token);
        } catch (ExpiredJwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("만료된 토큰");
        }

        String category = jwtUtil.getCategory(refresh_token);
        if(!category.equals("refresh")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지않은 토큰임");
        }

        String username = jwtUtil.getUsername(refresh_token);
        String role = jwtUtil.getRole(refresh_token);

        String access= this.jwtUtil.createToken("access",username,role,5000L);

        response.addHeader("Authorization", "Bearer " + access);
        return ResponseEntity.status(HttpStatus.OK).body("토큰 발급 성공");
    }
}
