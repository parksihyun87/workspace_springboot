package com.example.practicingbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository// 데이터 접근 계층(DAO) 컴퍼넌트로 인식하게 해 주며, jpaReopositoy 상속(crud메서드 자동 제공)와 함께 2개를 같이 쓴다.
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
