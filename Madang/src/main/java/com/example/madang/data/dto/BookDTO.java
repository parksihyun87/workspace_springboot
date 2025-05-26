package com.example.madang.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Integer id;
    private String bookname;
    private String publisher;
    private Integer price;
    private Integer bookamount;
    private List<OrderInfoDTO> orders;
}
