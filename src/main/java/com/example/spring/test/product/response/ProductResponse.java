package com.example.spring.test.product.response;

import lombok.*;

@Getter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String productName;
    private Long price;

    @Builder
    public ProductResponse(Long id, String productName, Long price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }
}
