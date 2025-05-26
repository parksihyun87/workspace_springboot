package com.example.madang.service;

import com.example.madang.data.dao.OrderDAO;
import com.example.madang.data.dto.OrderDTO;
import com.example.madang.data.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;

    public List<OrderDTO> getBeforeOrderlist(LocalDate date){
        List<OrderDTO> newDTOList=new ArrayList<>();
        List<OrderEntity> getList=this.orderDAO.getBeforeOrderlist(date);
        if(getList.size()==0){
            throw new RuntimeException("아무 값도 없습니다.");
        }
        for(OrderEntity entitylist:getList){
            OrderDTO newDTO=OrderDTO.builder()
                    .id(entitylist.getId())
                    .custid(entitylist.getCust().getId())
                    .bookid(entitylist.getBook().getId())
                    .saleprice(entitylist.getSaleprice())
                    .orderdate(entitylist.getOrderdate())
                    .ordamount(entitylist.getOrdamount())
                    .build();
            newDTOList.add(newDTO);
        }
        return newDTOList;
    }

    public List<OrderDTO> getAfterOrderlist(LocalDate date){
        List<OrderDTO> newDTOList=new ArrayList<>();
        List<OrderEntity> getList=this.orderDAO.getAfterOrderlist(date);
        if(getList.size()==0){
            throw new RuntimeException("아무 값도 없습니다.");
        }
        for(OrderEntity entitylist:getList){
            OrderDTO newDTO=OrderDTO.builder()
                    .id(entitylist.getId())
                    .custid(entitylist.getCust().getId())
                    .bookid(entitylist.getBook().getId())
                    .saleprice(entitylist.getSaleprice())
                    .orderdate(entitylist.getOrderdate())
                    .ordamount(entitylist.getOrdamount())
                    .build();
            newDTOList.add(newDTO);
        }
        return newDTOList;
    }
}
