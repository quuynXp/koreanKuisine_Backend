package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrders(Orders order);
    List<OrderDetail> findByCartId(String cartId);
}

