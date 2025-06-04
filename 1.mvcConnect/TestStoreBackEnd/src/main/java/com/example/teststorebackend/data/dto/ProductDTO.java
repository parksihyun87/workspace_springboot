package com.example.teststorebackend.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//여기서 뭔가 넘겨주는 용도
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    @NotEmpty(message = "이름은 비어있을 수 없습니다.")
    private String title;
    @Min(value=0, message="가격은 0원 이상이어야 합니다.")
    private Integer price;
    private String imagesrc;
}
