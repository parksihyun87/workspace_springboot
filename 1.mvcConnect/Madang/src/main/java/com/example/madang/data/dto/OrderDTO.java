package com.example.madang.data.dto;

import com.example.madang.data.entity.BookEntity;
import com.example.madang.data.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private Integer custid;
    private Integer bookid;
    private Integer saleprice;
    private LocalDate orderdate;
    private Integer ordamount;
}
