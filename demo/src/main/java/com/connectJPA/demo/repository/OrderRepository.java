package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findByUser(User user);
}

