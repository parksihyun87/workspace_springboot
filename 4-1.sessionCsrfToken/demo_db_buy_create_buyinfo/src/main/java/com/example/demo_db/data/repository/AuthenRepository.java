package com.example.demo_db.data.repository;

import com.example.demo_db.data.entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenRepository extends JpaRepository<AuthenEntity,String> {
}
