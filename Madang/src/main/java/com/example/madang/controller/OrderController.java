package com.example.madang.controller;

import com.example.madang.data.dto.OrderDTO;
import com.example.madang.data.entity.CustomerEntity;
import com.example.madang.data.entity.OrderEntity;
import com.example.madang.data.repository.OrderEntityRepository;
import com.example.madang.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orderinfo")
public class OrderController {
    private final OrderService orderService;
    private final OrderEntityRepository orderEntityRepository;
    @GetMapping(value="orderlist")
    public ResponseEntity<List<OrderDTO>> getOrderlist(){
        List<OrderDTO> getList=this.orderService.getOrderlist();
        return ResponseEntity.ok(getList);
    }
}

//    @GetMapping(value = "/order/{id}")
//    public OrderEntity getOrder(@PathVariable Integer id){
//        OrderEntity order=this.orderEntityRepository.findById(id).orElse(null);
//        CustomerEntity cust = order.getCustid();
//        System.out.println(cust.getName()+cust.getAddress());
//        return order;
//    }