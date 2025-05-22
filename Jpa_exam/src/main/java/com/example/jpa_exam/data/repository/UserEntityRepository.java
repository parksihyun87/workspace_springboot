package com.example.jpa_exam.data.repository;

import com.example.jpa_exam.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity,String> {

    @Query(value = "select * from usertbl where addr=:addr", nativeQuery =true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr);

    @Query(value= "select * from usertbl where birthyear=:birthyear", nativeQuery = true)
    List<UserEntity> searchUserInfo(@Param("birthyear") Integer birthyear);

    @Query(value= "select * from usertbl where addr=:addr and birthyear=:birthyear", nativeQuery = true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr, @Param("birthyear") Integer birthyear);




//    @Query(value="insert into usertbl values(:userid, :username, :birthday, :addr, :mobile1, :mobile2, :mdate)",nativeQuery = true)
//    UserEntity addUserInfo(@Param("userid") String userid, @Param("username") String username,
//                           @Param("birthday") Integer birthday, @Param("addr") String addr,
//                           @Param("mobile1") String mobile1, @Param("mobile2") String mobile2, @Param("mdate") LocalDate mdate);
}


