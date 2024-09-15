package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.request.OrderDetailRequest;
import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.response.OrderDetailResponse;
import com.connectJPA.demo.entity.Orders;
import com.connectJPA.demo.entity.OrderDetail;
import com.connectJPA.demo.mapper.OrderDetailMapper;
import com.connectJPA.demo.repository.OrderRepository;
import com.connectJPA.demo.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/order-details")
public class OrderDetailController {
    OrderDetailService orderDetailService;
    OrderDetailMapper orderDetailMapper;
    OrderRepository orderRepository;

    @PostMapping("/create")
    public ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailRequest);

        // Assuming the OrderDetailService handles the persistence logic
        OrderDetail savedOrderDetail = orderDetailService.createOrderDetail(
                orderDetail.getOrders(), orderDetail.getProductId(),
                orderDetail.getProductName(),orderDetail.getProductType(),
                orderDetail.getQuantity(), orderDetail.getUnitPrice());

        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailMapper.toOrderDetailResponse(savedOrderDetail))
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailsByOrder(@PathVariable String orderId) {
        Optional<Orders> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            return ApiResponse.<List<OrderDetailResponse>>builder()
                    .result(Collections.emptyList())
                    .build();
        }
        Orders order = orderOptional.get();
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
        List<OrderDetailResponse> orderDetailResponses = orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());

        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailResponses)
                .build();
    }
}
