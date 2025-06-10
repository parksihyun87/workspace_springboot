package com.example.poseexam.controller;

import com.example.poseexam.data.dto.AuthenDTO;
import com.example.poseexam.data.entity.AuthenEntity;
import com.example.poseexam.data.repository.AuthenRepository;
import com.example.poseexam.service.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenController {
    private final AuthenService authenService;
    private final AuthenRepository  authenRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/join")
    public ResponseEntity<String> joinUser(@RequestBody AuthenDTO authenDTO){
        this.authenService.joinUser(authenDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
    }


    @PostMapping(value="/password")
    public String password(){
        List<AuthenEntity> list = authenRepository.findAll();
        for(AuthenEntity authenEntity : list){
            AuthenEntity entity= AuthenEntity.builder()
                    .username(authenEntity.getUsername())
                    .password(passwordEncoder.encode(authenEntity.getPassword()))
                    .writer(authenEntity.getWriter())
                    .role(authenEntity.getRole())
                    .build();
            this.authenRepository.save(entity);
        }
        return "success";
    }
}
