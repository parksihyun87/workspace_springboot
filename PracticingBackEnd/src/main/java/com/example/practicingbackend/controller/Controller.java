package com.example.practicingbackend.controller;

import com.example.practicingbackend.data.Product;
import com.example.practicingbackend.data.ProductDTO;
import com.example.practicingbackend.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController//레컨을 통해 요청반응을 보내므로, 자바를 json 형식으로 바꿔서 보냄.
@RequiredArgsConstructor//ras 라스
public class Controller {
    public final ProductRepository productRepository;

    @GetMapping(value = "/product-list")// 리스트 형식임, 일반적인 배열, 이 경우 db의 내용을 자바형식을 통해 js로 입력함.(product 형식으로 보냄)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping(value="/product-post")// 반응을 보낼때와 반대로 프론트에서 내용을 전달 받으며, requestbody를 통해 요청된 json을 java로 변환받음
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){//js에서 입력한 것이 java spring boot와 db로 오는셈. js의 형식으로 자바에 전달, 받으려면 클래스 형태로 한담에, db에 저장.다시 프론트엔드에 줄 데이터도 형성
        Product completeProduct= new Product();
        completeProduct.setTitle(productDTO.getTitle());
        completeProduct.setPrice(productDTO.getPrice());
        completeProduct.setImgSrc("https://dummyimage.com/200x200/00f/fff.jpg&text=product");
        Product saveProduct= this.productRepository.save(completeProduct);// 이 순간 아이디 생김.
        ProductDTO savedProductDTO = new ProductDTO();
        savedProductDTO.setTitle(saveProduct.getTitle());
        savedProductDTO.setPrice(saveProduct.getPrice());
        savedProductDTO.setImgSrc(saveProduct.getImgSrc());
        savedProductDTO.setId(saveProduct.getId());
        return savedProductDTO;
    }

    @PostMapping(value = "/product-update/")//해당 id 필요하고 이걸로 내용 입력시 수정
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){

    }

}
