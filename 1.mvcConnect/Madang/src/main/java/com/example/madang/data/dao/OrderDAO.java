package com.example.madang.data.dao;

import com.example.madang.data.entity.OrderEntity;
import com.example.madang.data.repository.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderEntityRepository orderEntityRepository;
    public List<OrderEntity> getBeforeOrderlist(LocalDate date){
        return this.orderEntityRepository.getBeforeOrderlist(date);
    }

    public List<OrderEntity> getAfterOrderlist(LocalDate date){
        return this.orderEntityRepository.getAfterOrderlist(date);
    }

}
