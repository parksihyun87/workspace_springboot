package com.example.jpa_exam.data.dao;

import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.data.entity.BuyEntity;
import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.data.repository.BuyEntityRepository;
import com.example.jpa_exam.data.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyDAO {
    private final BuyEntityRepository buyEntityRepository;
    private final UserEntityRepository userEntityRepository;

    public List<BuyEntity> userBuyList(String userid){
        return this.buyEntityRepository.findAllListById(userid);
    }

    public BuyEntity addbuyproduct(String userid, String prodname, String groupname, Integer price, Short amount){
        Optional<UserEntity> selectUser=userEntityRepository.findById(userid);// 파인드 바이는 기본키로해야함.
        UserEntity userget = null;
        if(selectUser.isPresent()){
             userget=selectUser.get();
        }
        BuyEntity buyEntity = BuyEntity.builder()
                .user(userget)
                .prodname(prodname)
                .groupname(groupname)
                .price(price)
                .amount(amount)
                .build();
        return this.buyEntityRepository.save(buyEntity);
    }
}
