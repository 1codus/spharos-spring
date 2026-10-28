package com.example.spring.test.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
//@Setter 쓰면 안됨
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private boolean deleted = false;

    //세터를 만들되 이름을 지정해서 잘못 쓰는 경우가 없도록 최대한 상세하게 이름을 지정
    public void changePriceByAdmin(Long price) {
        this.price = price;
    }

    public void softDeleteByUser(){
        this.deleted = true;
    }
}
