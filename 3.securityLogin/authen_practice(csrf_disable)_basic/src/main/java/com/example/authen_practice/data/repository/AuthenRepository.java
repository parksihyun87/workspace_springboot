package com.example.authen_practice.data.repository;

import com.example.authen_practice.data.entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenRepository extends JpaRepository<AuthenEntity,String> {

}
