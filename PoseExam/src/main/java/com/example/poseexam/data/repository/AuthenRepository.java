package com.example.poseexam.data.repository;

import com.example.poseexam.data.entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenRepository extends JpaRepository<AuthenEntity, String> {
}
