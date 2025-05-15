package com.example.teststorebackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//여기서 뭔가 넘겨주는 용도
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String title;
    private Integer price;
    private String imagesrc;
}
