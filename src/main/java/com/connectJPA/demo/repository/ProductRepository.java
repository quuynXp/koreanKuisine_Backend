package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository<T extends Product> extends JpaRepository<T, String> {
    List<Product> findByIdIn(Iterable<String> ids);
}
