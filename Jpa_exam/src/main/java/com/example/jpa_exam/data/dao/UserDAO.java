package com.example.jpa_exam.data.dao;

import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.data.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDAO {
    private final UserEntityRepository userEntityRepository;

    public List<UserEntity> getAllUserList(){
        return this.userEntityRepository.findAll();
    }

    public List<UserEntity> getUserInfoByAddr( String addr){
        return this.userEntityRepository.searchUserInfo(addr);
    }

    public List<UserEntity> getUserInfoByBirth(Integer birthyear){
        return this.userEntityRepository.searchUserInfo(birthyear);
    }

    public List<UserEntity> getUserInfoByAandB(String addr, Integer birthyear){
        return this.userEntityRepository.searchUserInfo(addr,birthyear);
    }
}
