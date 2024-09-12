package com.connectJPA.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddProductRequest {
    String cartId;
    String productId;
    BigDecimal quantity;
    String productType;
    BigDecimal unitPrice;
    String userId;
}
