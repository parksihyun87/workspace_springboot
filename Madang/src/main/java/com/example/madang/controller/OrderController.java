package com.example.madang.controller;

import com.example.madang.data.dto.OrderDTO;
import com.example.madang.data.entity.CustomerEntity;
import com.example.madang.data.entity.OrderEntity;
import com.example.madang.data.repository.OrderEntityRepository;
import com.example.madang.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orderinfo")
public class OrderController {
    private final OrderService orderService;
    private final OrderEntityRepository orderEntityRepository;


    @GetMapping(value="orderbeforelist")
    public ResponseEntity<List<OrderDTO>> getBeforeOrderlist(@RequestParam LocalDate orderdate){
        List<OrderDTO> getList=this.orderService.getBeforeOrderlist(orderdate);
        return ResponseEntity.ok(getList);
    }

    @GetMapping(value="orderafterlist")
    public ResponseEntity<List<OrderDTO>> getAfterOrderlist(@RequestParam LocalDate orderdate){
        List<OrderDTO> getList=this.orderService.getAfterOrderlist(orderdate);
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