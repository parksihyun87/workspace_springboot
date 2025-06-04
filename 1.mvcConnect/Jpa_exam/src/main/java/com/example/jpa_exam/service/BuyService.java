package com.example.jpa_exam.service;

import com.example.jpa_exam.data.dao.BuyDAO;
import com.example.jpa_exam.data.dto.BuyDTO;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.data.entity.BuyEntity;
import com.example.jpa_exam.data.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final BuyDAO buyDAO;
    public List<BuyDTO> userBuyList(String userid){
        List<BuyDTO> buyDTOList = new ArrayList<>();
        List<BuyEntity> buyList =this.buyDAO.userBuyList(userid);
        if(buyList.size()==0){
            throw new EntityNotFoundException("내용을 찾을 수 없습니다.");
        }
        for(BuyEntity buy:buyList){
            BuyDTO buyDTO = BuyDTO.builder()
                    .id(buy.getId())
                    .user(buy.getUser())
                    .prodname(buy.getProdname())
                    .groupname(buy.getGroupname())
                    .price(buy.getPrice())
                    .amount(buy.getAmount())
                    .build();
            buyDTOList.add(buyDTO);
        }
        return buyDTOList;
    }

    public BuyDTO addbuyproduct(BuyDTO buyDTO){
        BuyEntity addproduct = this.buyDAO.addbuyproduct(buyDTO.getUser().getUserid(),buyDTO.getProdname(),buyDTO.getGroupname(),buyDTO.getPrice(),buyDTO.getAmount());
        if(addproduct==null){
            throw new EntityNotFoundException("상품기록 null로 오류를 발생시켰습니다.");
        }
        BuyDTO buyDTOGet =  BuyDTO.builder()
                .user(addproduct.getUser())
                .prodname(addproduct.getProdname())
                .groupname(addproduct.getGroupname())
                .price(addproduct.getPrice())
                .amount(addproduct.getAmount())
                .build();
        return buyDTOGet;
    }
}
