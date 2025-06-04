package com.example.jpa_exam.service;

import com.example.jpa_exam.controller.UserController;
import com.example.jpa_exam.data.dao.UserDAO;
import com.example.jpa_exam.data.dto.BuyDTO;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.data.entity.BuyEntity;
import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.exception.MyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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

    public UserDTO addUserInfo(UserDTO userDTO){
        UserEntity selectUser = this.userDAO.addUserInfo(userDTO.getUserid(),userDTO.getUsername(),userDTO.getBirthyear(),userDTO.getAddr(),userDTO.getMobile1(),userDTO.getMobile2(),userDTO.getHeight(), userDTO.getMdate());
        if(selectUser==null){
            throw new EntityNotFoundException("유저 null로 오류를 발생시켰습니다.");
        }
            UserDTO userDTOGet =  UserDTO.builder()
                    .userid(selectUser.getUserid())
                    .username(selectUser.getUsername())
                    .birthyear(selectUser.getBirthyear())
                    .addr(selectUser.getAddr())
                    .mobile1(selectUser.getMobile1())
                    .mobile2(selectUser.getMobile2())
                    .height(selectUser.getHeight())
                    .mdate(selectUser.getMdate())
                    .build();
        return userDTOGet;
    }


    public Boolean getDupliUserById(String userid){
        return this.userDAO.getDupliUserById(userid);
    }
}

//서비스에서 c는 DTO를 만들어 반환해주고, 넘어온 dto 단에서 전달하려는 값을 매개변수에다 풀어놓아 준다.
// r은 서비스에서 DTO만 만들어 반환해줌
// r과 u은 DTO만들어 반환해주고, 넘어온 dto 단에서 전달하려는 값을 매개변수에다 풀어주되 일부만 하고(r은 다함) dao가서 직접 저장은 안하고 객체 만들어 세이브
// D은 트루 폴스 반환하도록 만들어서(할거없음 옵셔널해) 마지막 dao에는 익시스트로 값이 있으면 삭제한다 정도.
