package com.example.teststorebackend.controller;

import com.example.teststorebackend.data.entity.Product;
import com.example.teststorebackend.data.dto.ProductDTO;
import com.example.teststorebackend.data.repository.ProductRepository;
import com.example.teststorebackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController// 응답 요청을 받아서 api로 만들어 줌.
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;

//    @GetMapping(value="/product-list")
//    public List<Product> getProductList(){
//        return this.productRepository.findAll();
//    }
//    @GetMapping(value="/product-list")
//    public ResponseEntity<List<ProductDTO>> getProductList(){
//        List<ProductDTO> productDTOlist = new ArrayList<>();
//        List<Product> productList = productRepository.findAll();
//        for (Product product : productList) {
//            ProductDTO productDTO = ProductDTO.builder()
//                    .id(product.getId())
//                    .title(product.getTitle())
//                    .price(product.getPrice())
//                    .imagesrc(product.getImagesrc())//set한건가?
//                    .build();
//            productDTOlist.add(productDTO);
//        }
//        return ResponseEntity.ok(productDTOlist);
//    }

    @GetMapping(value="/product-list")
    public ResponseEntity<List<ProductDTO>> getProductList(){
        List<ProductDTO> productDTOList = this.productService.getAllProducts();
        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping(value="/new-product")
    //id 오토 인크리? 확인 어제 어노테이션
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO){
        ProductDTO savedProductDTO = this.productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @PutMapping(value="/product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO){
        ProductDTO updateProductDTO = this.productService.updateProductById(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateProductDTO);
//        if(updateProductDTO!=null){
//
//        }
//            return ResponseEntity.status(HttpStatus.OK).build();
        }
        //원래 그냥 널이었음. 상태 코드는 둘다 200으로 성공이다. 이걸 받은 측에서 성공확인할려면 바디에 뭐가 있나 없나를 확인해야만 하는데, 이렇게 하면 400번대로 들어가서, 리스폰스.ok가 폴스가 나게 된다.

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){// 리스폰스 엔티티에 있는것이 문자열이다라고 자료형
        // id가 있는지 부터 확인
        this.productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("상품삭제성공");
//        if(result){
//        }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제상품검색 실패");
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductDTO> getException(@PathVariable Integer id){
        ProductDTO productDTO = this.productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    //여기 자체에서 발생하는 예외(자료받다 오류등)자체의 처리를 위한 예외를 처리하는 것 만듬/ 위으 밸리드를 대상이기에 지역적
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach((error)->{
//            errors.put(error.getField(),error.getDefaultMessage());
//        });
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
//    }

//    @GetMapping(value = "/exception")
//    public ResponseEntity<String > getException(){
//        try{
//            throw new Exception("그냥 예외");// 로직 상 예외이긴때문에 일부러 만든거임.
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }


}
