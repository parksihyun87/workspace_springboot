package com.example.jpa_exam.data.dao;

import com.example.jpa_exam.data.dto.BuyDTO;
import com.example.jpa_exam.data.entity.BuyEntity;
import com.example.jpa_exam.data.repository.BuyEntityRepository;
import com.example.jpa_exam.service.BuyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyDAO {
    private final BuyEntityRepository buyEntityRepository;

    public List<BuyEntity> userBuyList(String userid){
        return this.buyEntityRepository.findAllListById(userid);
    }
}
