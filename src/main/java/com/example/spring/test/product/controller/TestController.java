package com.example.spring.test.product.controller;

import com.example.spring.test.product.request.PatchProductRequest;
import com.example.spring.test.product.request.UpdateProductRequest;
import com.example.spring.test.product.response.ProductResponse;
import com.example.spring.test.product.entity.Product;
import com.example.spring.test.product.request.ProductRequest;
import com.example.spring.test.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class TestController {

    private final ProductService productService;

    @GetMapping("/test")
    public String test() {
        return "정상적으로 실행됨";
    }

    @GetMapping
    public List<Product> product() {

//        ProductService productService = new ProductService();
        // 실제 상품 조회하는 메서드 호출
        List<Product> result = productService.productList();

        return result;
    }

    @GetMapping("/{productId}")
    public ProductResponse productById(@PathVariable Long productId) {
        ProductResponse product = productService.product(productId);

        return product;
    }

    @GetMapping("/list")
    public String productList(@RequestParam Long price,
                              @RequestParam String name
    ) {
        return name + ":" + price;
    }

    @PostMapping
    public void createProduct(@RequestBody ProductRequest productRequest) {
        // 프로덕트 서비스의 createProduct 호출
        productService.createProduct(productRequest);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@RequestBody UpdateProductRequest updateProductRequest,@PathVariable Long productId){
        productService.updateProduct(updateProductRequest, productId);
    }

    @PatchMapping("/{productId}")
    public void patchProduct(@RequestBody PatchProductRequest patchProductRequest, @PathVariable Long productId){
        productService.patchProduct(patchProductRequest, productId);
    }
//    @DeleteMapping("/{productId}") //하드딜리트
//    public void deleteProduct(@PathVariable Long productId){
//        //productService 에 deleteProduct 호출 <- 아이디 넘겨줌
//        productService.deleteProduct(productId);
//    }
    @DeleteMapping("/{productId}/soft") //소프트딜리트
    public void softDeleteProduct(@PathVariable Long productId){
        //productService 에 softDeleteProduct 호출 <- 아이디 넘겨줌
        productService.softDeleteProduct(productId);
    }
}