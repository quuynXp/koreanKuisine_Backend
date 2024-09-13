package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.request.OrderDetailRequest;
import com.connectJPA.demo.dto.request.OrderRequest;
import com.connectJPA.demo.dto.response.*;
import com.connectJPA.demo.entity.OrderDetail;
import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.mapper.OrderMapper;
import com.connectJPA.demo.repository.UserRepository;
import com.connectJPA.demo.service.OrderDetailService;
import com.connectJPA.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;
    UserRepository userRepository;
    OrderMapper orderMapper;
    OrderDetailService orderDetailService;

    @GetMapping("/all")
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        List<OrderResponse> orderResponses = orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());

        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponses)
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.getUserId() == null) {
            return ApiResponse.<OrderResponse>builder()
                    .message("UserId không thể là null.")
                    .build();
        }

        Optional<User> userOptional = userRepository.findById(orderRequest.getUserId());
        if (userOptional.isEmpty()) {
            return ApiResponse.<OrderResponse>builder()
                    .message("Người dùng không tồn tại.")
                    .build();
        }
        User user = userOptional.get();

        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            return ApiResponse.<OrderResponse>builder()
                    .message("Danh sách item không thể là null hoặc rỗng.")
                    .build();
        }

        List<OrderDetail> orderDetails = orderRequest.getItems().stream().map(item -> {
            OrderDetail detail = new OrderDetail();
            detail.setProductName(item.getProductName());
            detail.setQuantity(item.getQuantity());
            detail.setProductType(item.getProductType());
            detail.setUnitPrice(item.getUnitPrice());
            detail.setTotalPrice(item.getUnitPrice().multiply(item.getQuantity()));
            return detail;
        }).collect(Collectors.toList());

        Orders savedOrder = orderService.createOrder(user, orderDetails, orderRequest.getPromoCode(), orderRequest.getPaymentMethod());

        OrderResponse orderResponse = orderMapper.toOrderResponse(savedOrder);

        return ApiResponse.<OrderResponse>builder()
                .result(orderResponse)
                .message("Đơn hàng đã được tạo thành công.")
                .build();
    }









    @GetMapping("/{userId}")
    public ApiResponse<List<OrderResponse>> getOrdersByUser(@PathVariable String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ApiResponse.<List<OrderResponse>>builder()
                    .result(Collections.emptyList())
                    .build();
        }
        User user = userOptional.get();

        List<Orders> orders = orderService.getOrdersByUser(user);
        List<OrderResponse> orderResponses = orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());

        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponses)
                .build();
    }
}


