package com.example.teststorebackend.data.dao;

import com.example.teststorebackend.data.entity.Product;
import com.example.teststorebackend.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductDAO {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(Integer id){
        Optional<Product> product = this.productRepository.findById(id);// 옵셔날을 리턴함/productRepository.findById(id)
        return product.orElse(null);// 존재하냐 여부 확인. 존재하면 리턴한다. 옵셔날
    }

    public Product saveProduct(String title, String imagesrc, Integer price){
        Product product = Product.builder()
                .title(title)
                .imagesrc(imagesrc)
                .price(price)
                .build();
        return this.productRepository.save(product);
    }

    public boolean deleteProductById(Integer id){
        if(productRepository.existsById(id)){
            this.productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product updateProductById(Integer id,Integer price){
        Optional<Product> product = this.productRepository.findById(id);
        Product updateProduct = product.orElse(null);
        if(updateProduct != null){
            updateProduct.setPrice(price);
            return this.productRepository.save(updateProduct);
        }
        return null;
    }
}
