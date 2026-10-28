package com.example.spring.test.cart.service;

import com.example.spring.test.cart.entity.Cart;
import com.example.spring.test.cart.repository.CartRepository;
import com.example.spring.test.cart.request.CreateCartRequest;
import com.example.spring.test.member.entity.Member;
import com.example.spring.test.member.repository.MemberRepository;
import com.example.spring.test.product.entity.Product;
import com.example.spring.test.product.repository.ProductRepository;
import com.example.spring.test.product.response.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createCart(CreateCartRequest createCartRequest){
        Product product = productRepository.findById(createCartRequest.getProductId()).get();
        Member member = memberRepository.findById(createCartRequest.getMemberId()).get();

        Cart cart = Cart.builder()
                .product(product)
                .member(member)
                .build();
        cartRepository.save(cart);

    }

    public List<ProductResponse> getCart(Long memberId) {
        List<Cart> carts = cartRepository.findAllByMemberId(memberId);
        List<ProductResponse> result = new ArrayList<>();

        for(Cart item : carts) {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(item.getProduct().getId())
                    .price(item.getProduct().getPrice())
                    .productName(item.getProduct().getName())
                    .build();
            result.add(productResponse);
        }
        return result;
    }
}
