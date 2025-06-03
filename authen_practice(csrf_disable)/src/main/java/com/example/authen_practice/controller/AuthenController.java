package com.example.authen_practice.controller;

import com.example.authen_practice.data.dto.AuthenDTO;
import com.example.authen_practice.data.entity.AuthenEntity;
import com.example.authen_practice.data.repository.AuthenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenController {
    private final AuthenRepository authenRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/")
    public String amdin(){
        return "Hello Admin";
    }

    @PostMapping(value="/join")
    public ResponseEntity<String> join(@RequestBody AuthenDTO authenDTO) {
        if (this.authenRepository.existsById(authenDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        }
        AuthenEntity authenEntity = AuthenEntity.builder()
                .username(authenDTO.getUsername())
                .password(passwordEncoder.encode(authenDTO.getPassword()))// 암호화 하는 과정
                .role("ROLE_USER")
                .build();
        this.authenRepository.save(authenEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");// 큰Http에 담아다가 상태를 표시.
    }
}
