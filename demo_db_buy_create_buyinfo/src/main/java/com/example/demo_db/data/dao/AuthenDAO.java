package com.example.demo_db.data.dao;

import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenDAO {
    private final AuthenRepository authenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenEntity joinAdmin(String username, String password) {
        AuthenEntity authenEntity=AuthenEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role("ROLE_ADMIN")
                .build();
        boolean flag=this.authenRepository.existsById(authenEntity.getUsername());
        if(!flag){
            return this.authenRepository.save(authenEntity);
        }
        return null;
    }
}
