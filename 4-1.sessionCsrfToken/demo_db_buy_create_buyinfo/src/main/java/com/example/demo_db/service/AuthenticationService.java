package com.example.demo_db.service;

import com.example.demo_db.data.dao.AuthenDAO;
import com.example.demo_db.data.dto.AuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.exception.AuthenticationErrorException;
import com.example.demo_db.exception.DuplicateIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final AuthenDAO authenDAO;

    public AuthenDTO joinAdmin(AuthenDTO authenDTO) {
        AuthenEntity authenEntity= this.authenDAO.joinAdmin(authenDTO.getUsername(), authenDTO.getPassword());
        if(!(authenDTO.getConfirm().equals("1234"))){
            throw new AuthenticationErrorException("인증번호 오류입니다..");
        }
        if(authenEntity!=null){
            AuthenDTO SaveAuthenDTO= AuthenDTO.builder()
                    .username(authenEntity.getUsername())
                    .password(authenEntity.getPassword())
                    .build();
            return SaveAuthenDTO;
        }
        throw new DuplicateIdException("해당 아이디는 중복입니다.");
    }
}
