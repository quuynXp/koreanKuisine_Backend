package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.*;
import com.connectJPA.demo.repository.CartRepository;
import com.connectJPA.demo.repository.OrderRepository;
import com.connectJPA.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    CartRepository cartRepository;

    public void addProductToOrder(Orders order, Product product, BigDecimal quantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductName(product.getName());
        orderDetail.setQuantity(quantity);
        orderDetail.setProductType(product.getType());
        orderDetail.setUnitPrice(product.getPrice());
        orderDetail.setTotalPrice(product.getPrice().multiply(quantity));

        order.addOrderDetail(orderDetail);

        orderRepository.save(order);
    }


    public void addDishToOrder(Orders order, Dish dish, BigDecimal quantity) {
        addProductToOrder(order, dish, quantity);
    }

    public void addDrinkToOrder(Orders order, Drinks drink, BigDecimal quantity) {
        addProductToOrder(order, drink, quantity);
    }

    public Orders createOrder(User user, List<OrderDetail> orderDetails, String promoCode, String paymentMethod) {
        Orders order = new Orders();
        order.setUser(user);
        order.setPromoCode(promoCode);
        order.setPaymentMethod(paymentMethod);
        order.setCart(user.getCart());
        order.setOrdersDate(LocalDateTime.now());

        for (OrderDetail detail : orderDetails) {
            order.addOrderDetail(detail);
        }
        
        orderRepository.save(order);

        Cart cart = user.getCart();
        cart.getOrderDetails().clear();
        cart.setTotalAmount(BigDecimal.ZERO);

        user.getCart().setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);

        return order;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

}