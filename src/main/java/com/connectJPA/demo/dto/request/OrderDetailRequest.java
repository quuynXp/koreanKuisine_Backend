package com.connectJPA.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {
    String productId;
    String productName;
    String productType; // "dish" hoặc "drinks"
    BigDecimal unitPrice;
    String cartId;
    BigDecimal quantity;
}


