package com.connectJPA.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String orderId;
    String userId;
    LocalDateTime orderDate;
    String paymentMethod;
    String promoCode;
    BigDecimal totalAmount;
    List<OrderDetailResponse> orderDetailResponses;
}

