package com.example.madang.data.repository;

import com.example.madang.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {// 위에는 (sql문,네크) 2가지만 적음, 밑에는 리스트에 함수 기본 틀만 잡음
    @Query(value="select * from orderstbl where orderdate<=:date",nativeQuery = true)
    public List<OrderEntity> getBeforeOrderlist(@Param("date") LocalDate date);

    @Query(value="select * from orderstbl where orderdate>=:date",nativeQuery = true)
    public List<OrderEntity> getAfterOrderlist(@Param("date") LocalDate date);
}
