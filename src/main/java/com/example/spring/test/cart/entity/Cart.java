package com.example.spring.test.cart.entity;

import com.example.spring.test.member.entity.Member;
import com.example.spring.test.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //특별한 경우가 아니면 달아두셈 다
    @JoinColumn(name = "product_id") //필수는 아님 이거는
    private Product product; //db에서는 product_id로 저장됨

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


}
