package com.example.demo_db.controller;

import com.example.demo_db.data.dto.AuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenRepository;
import com.example.demo_db.jwt.JwtLoginFilter;
import com.example.demo_db.jwt.JwtUtil;
import com.example.demo_db.service.AuthenticationService;
import com.example.demo_db.service.UserAuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthenController {
    private final JwtUtil jwtUtil;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenRepository authenRepository;

    @PostMapping(value="/joinadmin")
    public ResponseEntity<String> joinAdmin(@Valid @RequestBody AuthenDTO authenDTO) {
        this.authenticationService.joinAdmin(authenDTO);// 어슨 디티오 반환할까?
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
    }

    @DeleteMapping(value = "/refresh-cookie")// 나중에 delete로 실행
    public ResponseEntity<String> deleteToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie("refresh",null);
            cookie.setPath("/reissue");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("리프레시 토큰 null 대입");
    }

}
