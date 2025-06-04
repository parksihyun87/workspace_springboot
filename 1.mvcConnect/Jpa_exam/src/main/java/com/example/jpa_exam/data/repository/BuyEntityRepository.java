package com.example.jpa_exam.data.repository;

import com.example.jpa_exam.data.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyEntityRepository extends JpaRepository<BuyEntity, String> {
    @Query(value ="select * from buytbl where userid=:userid", nativeQuery = true )
    List<BuyEntity> findAllListById(@Param("userid") String userid);
    //(쿼,(발,는,말)3, 네쿼)5가지, (리,화,자,함,파) 5가지

}
