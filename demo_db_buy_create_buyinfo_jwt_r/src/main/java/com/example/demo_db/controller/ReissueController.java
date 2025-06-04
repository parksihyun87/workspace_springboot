package com.example.demo_db.controller;


import com.example.demo_db.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {// 해당 api로 access토큰을 reissue 함, 일단 refresh 토큰을 가져와야 하므로 쿠키가 담겨있는 response가 필요하며
    private final JwtUtil jwtUtil;

    @PostMapping(value="/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response){// refresh담긴 쿠키는 요청에 있음, access는 응답에 있음// 리스폰스 엔티티 준비
        String refresh_token = null;// 이름 약간 다르게 함
        Cookie[] cookies = request.getCookies();// 쿠키에서 리프레시 토큰 분해 시작
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refresh_token = cookie.getValue();
                break;
            }
        }
        // 1.담은 쿠키 null 확인
        if(refresh_token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 null");
        }
        //2. (토큰만료여부,try -catch 문) 2가지로 토큰 만료여부 확인
        try{
            this.jwtUtil.isExpired(refresh_token);
        } catch(ExpiredJwtException e){// 3.익스 파이얼드 익섹션임
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("만료된 토큰입니다.");
        }
        //4. 카테고리 확인으로 리프레시 맞는지
        String category= this.jwtUtil.getCategory(refresh_token);
        if(!category.equals("refresh")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }
        // 여기까지 리프레시 토큰은 (1.null 2.만료여부 3.카테고리이름) 3가지 확인

        //5. (유저네임, 롤)2가지 담아서 다시 억세스 토큰 만든다.
        String username = this.jwtUtil.getUsername(refresh_token);
        String role= this.jwtUtil.getRole(refresh_token);

        String access= this.jwtUtil.createToken("access",username,role,5000L);

        //이제 헤더에 포함
        response.addHeader("Authorization","Bearer "+access);

        return ResponseEntity.status(HttpStatus.OK).body("토큰 발급 성공");
    }
}
