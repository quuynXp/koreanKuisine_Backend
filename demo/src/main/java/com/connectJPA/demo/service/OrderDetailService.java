package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.OrderDetail;
import com.connectJPA.demo.repository.OrderDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    public OrderDetail createOrderDetail(Orders order,String productId, String productName, String productType, BigDecimal quantity, BigDecimal unitPrice) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrders(order);
        orderDetail.setProductId(productId);
        orderDetail.setProductName(productName);
        orderDetail.setProductType(productType);
        orderDetail.setQuantity(quantity);
        orderDetail.setUnitPrice(unitPrice);
        orderDetail.setTotalPrice(unitPrice.multiply(quantity));

        order.setTotalAmount(order.getTotalAmount().add(orderDetail.getTotalPrice()));

        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> getOrderDetailsByOrder(Orders order) {
        return orderDetailRepository.findByOrders(order);
    }
    public List<OrderDetail> getOrderDetailsByCartId(String cartId) {
        return orderDetailRepository.findByCartId(cartId);
    }

    public void saveOrderDetail(OrderDetail detail) {
         orderDetailRepository.save(detail); }
}

