package com.example.demo_db.controller;

import com.example.demo_db.data.dto.AuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenRepository;
import com.example.demo_db.service.AuthenticationService;
import com.example.demo_db.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthenController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenRepository authenRepository;

    @PostMapping(value="/joinadmin")
    public ResponseEntity<String> joinAdmin(@Valid @RequestBody AuthenDTO authenDTO) {
        this.authenticationService.joinAdmin(authenDTO);// 어슨 디티오 반환할까?
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
    }

//    @GetMapping(value="/csrf-token")
//    public ResponseEntity<Map<String,String>> csrfToken(HttpServletRequest request) {
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//        Map<String,String> map = new HashMap<>();
//        map.put("csrf-token", csrfToken.getToken());
//
//        return ResponseEntity.ok(map);
//    }
//
//    @PostMapping(value="/password")
//    public String password(){
//        List <AuthenEntity> list = authenRepository.findAll();
//        for(AuthenEntity authenEntity : list){
//            AuthenEntity entity= AuthenEntity.builder()
//                    .username(authenEntity.getUsername())
//                    .password(passwordEncoder.encode(authenEntity.getPassword()))
//                    .role(authenEntity.getRole())
//                    .build();
//            this.authenRepository.save(entity);
//        }
//        return "success";
//    }
}
