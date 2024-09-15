package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository<T extends Product> extends JpaRepository<T, String> {

}
