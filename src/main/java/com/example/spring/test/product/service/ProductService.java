package com.example.spring.test.product.service;

import com.example.spring.test.product.request.PatchProductRequest;
import com.example.spring.test.product.request.ProductRequest;
import com.example.spring.test.product.request.UpdateProductRequest;
import com.example.spring.test.product.response.ProductResponse;
import com.example.spring.test.product.entity.Product;
import com.example.spring.test.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> productList() {

        //db 조회
        List<Product> productList =  productRepository.findByDeletedFalse();

        return productList;
    }

    @Transactional
    public ProductResponse product(Long id) {
        Product product = productRepository.findById(id).get();

        //product.setPrice(30000000L);

        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .productName(product.getName())
                .build();

        return response;
    }

    @Transactional
    public void createProduct(ProductRequest productRequest){
        //Product 객체 생성
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        //product repository 호출해서 save() 함수 호출
        productRepository.save(product);

    }

    @Transactional //두 트랜잭션을 같이 묶어서 하나의 실행 단위로 수행함
    public void updateProduct(UpdateProductRequest updateProductRequest, Long productId){
        //기존에 있는 값을 수정해야하는데 그럼 기존 값을 먼저 조회해야함
        //1. 기존의 프로덕트 id로 조회 (트랜젝션 1 열렸다가 이거 끝나면 닫힘)
        Product oldproduct = productRepository.findById(productId).get();

        //buliler 사용
        Product newProduct = Product.builder()
                .id(oldproduct.getId())
                .name(updateProductRequest.getName())
                .price(updateProductRequest.getPrice())
                .build();

        // productRepository에 저장 (트랜잭션 2)
        productRepository.save(newProduct);
    }

    @Transactional
    public void patchProduct(PatchProductRequest patchProductRequest, Long productId){

        Product oldproduct = productRepository.findById(productId).get();
        //setter 사용
        oldproduct.changePriceByAdmin(patchProductRequest.getPrice());

    }

    @Transactional
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    @Transactional
    public void softDeleteProduct(Long productId){
        //딜리티드라는 값을 f -> t 로 만들어줘야함 (사실 상 삭제가 아니고 수정)
        Product product = productRepository.findById(productId).get();
        product.softDeleteByUser();
        //여기까지 한건 그냥 딜리티드 값만 바꾼거라서 조회할 떄 안보이게 조건을 수정해야함
    }

}
