package com.example.poseexam.service;

import com.example.poseexam.data.dao.AuthenDAO;
import com.example.poseexam.data.dto.AuthenDTO;
import com.example.poseexam.data.entity.AuthenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenService {
    private final AuthenDAO authenDAO;

    public AuthenDTO joinUser(AuthenDTO authenDTO) {

        AuthenEntity authenEntity =this.authenDAO.joinUser(authenDTO.getUsername(), authenDTO.getPassword(),authenDTO.getWriter());
        if(authenEntity!=null){
            AuthenDTO newAuthenDTO = AuthenDTO.builder()
                    .username(authenEntity.getUsername())
                    .writer(authenEntity.getWriter())
                    .role(authenEntity.getRole())
                    .build();
            return newAuthenDTO;
        }
        throw new RuntimeException("중복 오류 표시");
    }
}
