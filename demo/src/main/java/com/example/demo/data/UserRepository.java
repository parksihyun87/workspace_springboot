package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    // 유저 레포지토리라고 지어서 지맘대로 유저라고 예측해서 넣음.
    // 테이블에 매칭될, 스프링 부트가 자동으로 쿼리문을 만들어줌. 레포붙으면 자동으로 클래스도 만듬.
    // 오버라이딩도 알아서 함. 객체로 만들어서 컨테이너에 빈으로 만들어 놓음.

}
