package com.connectJPA.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    String productId;
    String productName;
    String productType;
    BigDecimal quantity;   // Đổi quantity thành BigDecimal
    BigDecimal unitPrice;
    BigDecimal totalPrice;
    String imageUrl;
}


