package com.example.teststorebackend.controller;

import com.example.teststorebackend.data.Product;
import com.example.teststorebackend.data.ProductDTO;
import com.example.teststorebackend.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController// 응답 요청을 받아서 api로 만들어 줌.
@RequiredArgsConstructor
public class productController {
    private final ProductRepository productRepository;

    @GetMapping(value="/product-list")
    public List<Product> getProductList(){
        return this.productRepository.findAll();
    }

    @PostMapping(value="/new-product")
    //id 오토 인크리? 확인 어제 어노테이션
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setTitle(productDTO.getTitle());
        product.setImagesrc("https://dummyimage.com/200x200/00f/fff.jpg&text=product");
        Product saveProduct= this.productRepository.save(product);
        ProductDTO saveProductDTO = new ProductDTO();
        saveProductDTO.setTitle(saveProduct.getTitle());
        saveProductDTO.setPrice(saveProduct.getPrice());
        saveProductDTO.setImagesrc(saveProduct.getImagesrc());
        saveProductDTO.setId(saveProduct.getId());
        return saveProductDTO;
    }

    @PutMapping(value="/product")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        //가져와서 고침. 똑같은 것의 기준.수정
        Optional <Product>productOp= this.productRepository.findById(productDTO.getId());
        if(productOp.isPresent()){
            Product product = productOp.get();// 래핑에서 꺼내옴
            product.setPrice(productDTO.getPrice());
            Product updateProduct= this.productRepository.save(product);
            ProductDTO updateProductDTO = new ProductDTO();
            updateProductDTO.setTitle(updateProduct.getTitle());
            updateProductDTO.setPrice(updateProduct.getPrice());
            updateProductDTO.setImagesrc(updateProduct.getImagesrc());
            updateProductDTO.setId(updateProduct.getId());
            return updateProductDTO;
        }
        return null;
    }
    @DeleteMapping(value = "/product/{id}")
    public String deleteProduct(@PathVariable Integer id){
        // id가 있는지 부터 확인
        if(this.productRepository.existsById(id)){
            this.productRepository.deleteById(id);
            return "product deleted successfully";
        }
        return "product not found";
    }
}
