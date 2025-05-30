package com.example.madangdb_project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CutomerDTO {
    private Integer custId;
    private String name;
    private String address;
    private String phone;
    private List<OrderInfoDTO> orders;
}
