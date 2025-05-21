package com.example.jpa_exam.service;

import com.example.jpa_exam.controller.UserController;
import com.example.jpa_exam.data.dao.UserDAO;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.exception.MyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public List<UserDTO> getAllUserList(){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userList =this.userDAO.getAllUserList();
        if(userList.size()==0){
            throw new MyException("내용을 찾을 수 없습니다.");
        }
        for(UserEntity user:userList){
            UserDTO userDTO = UserDTO.builder()
                    .userid(user.getUserid())
                    .username(user.getUsername())
                    .birthyear(user.getBirthyear())
                    .addr(user.getAddr())
                    .mobile1(user.getMobile1())
                    .mobile2(user.getMobile2())
                    .height(user.getHeight())
                    .mdate(user.getMdate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public List<UserDTO> getUserInfoByAddr(String addr){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userList =this.userDAO.getUserInfoByAddr(addr);
        if(userList.size()==0){
            throw new MyException("내용을 찾을 수 없습니다.");
        }
        for(UserEntity user:userList){
            UserDTO userDTO = UserDTO.builder()
                    .userid(user.getUserid())
                    .username(user.getUsername())
                    .birthyear(user.getBirthyear())
                    .addr(user.getAddr())
                    .mobile1(user.getMobile1())
                    .mobile2(user.getMobile2())
                    .height(user.getHeight())
                    .mdate(user.getMdate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public List<UserDTO> getUserInfoByBirth(Integer birthyear){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userList =this.userDAO.getUserInfoByBirth(birthyear);
        if(userList.size()==0){
            throw new MyException("내용을 찾을 수 없습니다.");
        }
        for(UserEntity user:userList){
            UserDTO userDTO = UserDTO.builder()
                    .userid(user.getUserid())
                    .username(user.getUsername())
                    .birthyear(user.getBirthyear())
                    .addr(user.getAddr())
                    .mobile1(user.getMobile1())
                    .mobile2(user.getMobile2())
                    .height(user.getHeight())
                    .mdate(user.getMdate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public List<UserDTO> getUserInfoByAandB(String addr,Integer birthyear){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userList =this.userDAO.getUserInfoByAandB(addr, birthyear);
        if(userList.size()==0){
            throw new EntityNotFoundException("내용을 찾을 수 없습니다.");
        }
        for(UserEntity user:userList){
            UserDTO userDTO = UserDTO.builder()
                    .userid(user.getUserid())
                    .username(user.getUsername())
                    .birthyear(user.getBirthyear())
                    .addr(user.getAddr())
                    .mobile1(user.getMobile1())
                    .mobile2(user.getMobile2())
                    .height(user.getHeight())
                    .mdate(user.getMdate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
