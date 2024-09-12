package com.connectJPA.demo.dto.response;

import com.connectJPA.demo.dto.request.OrderDetailRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id;
    String userId;
    List<OrderDetailResponse> items;
    BigDecimal totalAmount;
}
