package com.example.practicingbackend.controller;

import com.example.practicingbackend.data.Product;
import com.example.practicingbackend.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor//ras 라스
public class Controller {
    public final ProductRepository productRepository;

    @GetMapping(value = "/product-list")// 리스트 형식임, 일반적인 배열
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
