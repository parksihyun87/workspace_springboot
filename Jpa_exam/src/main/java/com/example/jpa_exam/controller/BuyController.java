package com.example.jpa_exam.controller;

import com.example.jpa_exam.data.dto.BuyDTO;
import com.example.jpa_exam.service.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/buyinfo")
public class BuyController {
    private final BuyService buyService;

    @GetMapping(value = "buylist/{userid}")
    public ResponseEntity<List<BuyDTO>> userBuyList(@PathVariable("userid") String userid){
        List<BuyDTO> buyList = this.buyService.userBuyList(userid);
        return ResponseEntity.ok(buyList);
    }
}
