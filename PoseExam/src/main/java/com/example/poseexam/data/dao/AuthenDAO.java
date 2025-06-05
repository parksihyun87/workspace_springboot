package com.example.poseexam.data.dao;

import com.example.poseexam.data.dto.AuthenDTO;
import com.example.poseexam.data.entity.AuthenEntity;
import com.example.poseexam.data.repository.AuthenRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenDAO {
    private final AuthenRepository authenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenEntity joinUser(String username, String password, String writer) {
        if(authenRepository.existsById(username)){
         return null;
        }
        AuthenEntity saveAuthenEntity = AuthenEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER")
                .writer(writer)
                .build();
        this.authenRepository.save(saveAuthenEntity);
        return saveAuthenEntity;
    }
}
