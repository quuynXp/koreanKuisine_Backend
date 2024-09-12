package com.connectJPA.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishResponse implements ProductResponse{
    String id;
    String name;
    BigDecimal price;
    String ingredients;
    String description;
    String type;
    double rating;
    String imageUrl;
    String images;
}
