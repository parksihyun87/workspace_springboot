package com.example.madang.service;

import com.example.madang.data.dao.OrderDAO;
import com.example.madang.data.dto.OrderDTO;
import com.example.madang.data.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;

    public List<OrderDTO> getOrderlist(){
        List<OrderDTO> newDTOList=new ArrayList<>();
        List<OrderEntity> getList=this.orderDAO.getOrderlist();
        if(getList.size()==0){
            throw new RuntimeException("아무 값도 없습니다.");
        }
        for(OrderEntity entitylist:getList){
            OrderDTO newDTO=OrderDTO.builder()
                    .id(entitylist.getId())
                    .custid(entitylist.getCustid().getId())
                    .bookid(entitylist.getBookid().getId())
                    .saleprice(entitylist.getSaleprice())
                    .orderdate(entitylist.getOrderdate())
                    .ordamount(entitylist.getOrdamount())
                    .build();
            newDTOList.add(newDTO);
        }
        return newDTOList;
    }
}
