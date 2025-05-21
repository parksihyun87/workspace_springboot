package com.example.jpa_exam.data.repository;

import com.example.jpa_exam.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from usertbl where addr=:addr", nativeQuery =true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr);

    @Query(value= "select * from usertbl where birthyear=:birthyear", nativeQuery = true)
    List<UserEntity> searchUserInfo(@Param("birthyear") Integer birthyear);

    @Query(value= "select * from usertbl where addr=:addr and birthyear=:birthyear", nativeQuery = true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr, @Param("birthyear") Integer birthyear);

}


