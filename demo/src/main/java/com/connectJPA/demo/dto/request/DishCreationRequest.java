package com.connectJPA.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping
public class DishCreationRequest {
    @Size(min = 8, message = "NAME_INVALID")
    String name;

    BigDecimal price;
    String ingredients;
    String description;
}
