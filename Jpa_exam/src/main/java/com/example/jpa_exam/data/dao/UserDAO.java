package com.example.jpa_exam.data.dao;

import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.data.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public UserEntity addUserInfo(String userid, String username, Integer birthday, String addr, String mobile1, String mobile2, short height, LocalDate mdate){
        UserEntity userEntity = UserEntity.builder()
                .userid(userid)
                .username(username)
                .birthyear(birthday)
                .addr(addr)
                .mobile1(mobile1)
                .mobile2(mobile2)
                .height(height)
                .mdate(mdate)
                .build();
        return this.userEntityRepository.save(userEntity);
    }

    public Boolean getDupliUserById(String userid){
        Optional<UserEntity> getid = this.userEntityRepository.findById(userid);
        if(getid.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
