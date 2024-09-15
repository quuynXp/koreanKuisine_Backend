package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.*;
import com.connectJPA.demo.enums.PromoCode;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    CartRepository cartRepository;
    UserRepository userRepository;
    CartService cartService;

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

    public Orders createOrder(User user, Cart cart, String promoCode, String paymentMethod) {
        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setUser(user);
        order.setPromoCode(promoCode);
        order.setPaymentMethod(paymentMethod);
        order.setOrdersDate(LocalDateTime.now());

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail cartOrderDetail : cart.getOrderDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(cartOrderDetail.getProductId());
            orderDetail.setProductName(cartOrderDetail.getProductName());
            orderDetail.setQuantity(cartOrderDetail.getQuantity());
            orderDetail.setUnitPrice(cartOrderDetail.getUnitPrice());
            orderDetail.setTotalPrice(cartOrderDetail.getTotalPrice());
            orderDetail.setOrders(order);
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);

        BigDecimal totalAmount = orderDetails.stream()
                .map(OrderDetail::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        BigDecimal discount = BigDecimal.ZERO;
        if (promoCode.equalsIgnoreCase(PromoCode.koreanKuisine.getPromoCode())) {
            discount = totalAmount.multiply(BigDecimal.valueOf(PromoCode.koreanKuisine.getDiscount())).divide(BigDecimal.valueOf(100));
        } else if (promoCode.equalsIgnoreCase(PromoCode.vip.getPromoCode())) {
            discount = totalAmount.multiply(BigDecimal.valueOf(PromoCode.vip.getDiscount())).divide(BigDecimal.valueOf(100));
        }
        order.setTotalAmount(totalAmount.subtract(discount));

        Orders savedOrder = orderRepository.save(order);

        return savedOrder;
    }





    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByUser(User user) {
        List<Orders> orders = orderRepository.findByUserIdWithDetails(user.getId());
        log.debug("Fetched orders: {}", orders);
        return orders;
    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

}