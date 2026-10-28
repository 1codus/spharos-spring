package com.example.spring.test.product.repository;

import com.example.spring.test.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDeletedFalse(); //jpa에서 f만 골라서 보겠다. jpa에서 알아서 골라서 보여줌
}
