package com.example.jpa_exam.data.dto;

import com.example.jpa_exam.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyDTO {
    private Integer id;
    private UserEntity user;
    private String prodname;
    private String groupname;
    private Integer price;
    private Short amount;
}
