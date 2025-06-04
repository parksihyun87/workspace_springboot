package com.example.jpa_exam.controller;

import com.example.jpa_exam.data.dto.BuyDTO;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.service.BuyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value="/addbuyproduct")
    public ResponseEntity<BuyDTO> addbuyproduct(@Valid @RequestBody BuyDTO buyDTO){
        BuyDTO buy = this.buyService.addbuyproduct(buyDTO);
        return ResponseEntity.ok(buy);
    }

}
