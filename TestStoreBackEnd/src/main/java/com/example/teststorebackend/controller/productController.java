package com.example.teststorebackend.controller;

import com.example.teststorebackend.data.Product;
import com.example.teststorebackend.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController// 응답 요청을 받아서 api로 만들어 줌.
@RequiredArgsConstructor
public class productController {
    private final ProductRepository productRepository;

    @GetMapping(value="product-list")
    public List<Product> getProductList(){
        return this.productRepository.findAll();
    }
}
