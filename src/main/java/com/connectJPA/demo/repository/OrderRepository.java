package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Query("SELECT o FROM Orders o LEFT JOIN FETCH o.orderDetails WHERE o.user.id = :userId")
    List<Orders> findByUserIdWithDetails(@Param("userId") String userId);


}

