package com.example.jpa_exam.controller;

import com.example.jpa_exam.data.entity.BuyEntity;
import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.data.repository.BuyEntityRepository;
import com.example.jpa_exam.data.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class userController {
    private final UserEntityRepository userEntityRepository;
    private final BuyEntityRepository buyEntityRepository;

    @GetMapping(value = "/user-list")
    public List<UserEntity> userList(){
        return this.userEntityRepository.findAll();
    }

    @GetMapping(value="/buy-list")
    public List<BuyEntity> buyList(){
        return this.buyEntityRepository.findAll();
    }

    @GetMapping(value = "/userinfo/{addr}")
    public List<UserEntity> userInfo(@PathVariable("addr") String addr){
        return this.userEntityRepository.searchUserInfo(addr);
    }
}
