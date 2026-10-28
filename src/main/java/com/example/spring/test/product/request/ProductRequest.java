package com.example.spring.test.product.request;

import lombok.Getter;

@Getter
public class ProductRequest { //이 값을 프론트에서 받는거임
    private String name;
    private Long price;
}
