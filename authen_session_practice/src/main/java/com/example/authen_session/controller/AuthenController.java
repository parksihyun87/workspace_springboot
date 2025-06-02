package com.example.authen_session.controller;

import com.example.authen_session.data.dto.AuthenDTO;
import com.example.authen_session.data.entity.AuthenEntity;
import com.example.authen_session.data.repository.AuthenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenController {
    private final AuthenRepository authenRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/")
    public String index() {
        return "Hello World";
    }

    @GetMapping(value="/admin")
    public String admin() {
        return "Hello Admin";
    }

    @PostMapping(value = "/join")
    public ResponseEntity<String> join(@RequestBody AuthenDTO authenDTO) {
        if(this.authenRepository.existsById(authenDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        }

        AuthenEntity authenEntity = AuthenEntity.builder()
                .username(authenDTO.getUsername())
                .password(this.passwordEncoder.encode(authenDTO.getPassword()))// 이걸 하면 암호화해서 채워줌
                .role("ROLE_USER").build();
        this.authenRepository.save(authenEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
    }

    @GetMapping(value = "/csrf-token")
    public ResponseEntity<Map<String,String>> csrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());// 언더바도 되긴하지만, CsrfToken.class.getName() 이게 더 나음. 겟 어트리뷰트가 리턴한건 토큰 객체

        Map<String,String> map = new HashMap<>();
        map.put("csrf-token", csrfToken.getToken());// 토큰 문자열만 있으면 되므로, 문자열만 선택함

        return ResponseEntity.ok(map);// 프론트에게 바디부로 보냄.
    }

}

