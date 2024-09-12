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
public class DrinksResponse implements ProductResponse{
    String id;
    String name;
    BigDecimal price;
    String ingredients;
    String description;
    String origin;
    String company;
    String type;
    double rating;
    String imageUrl;
    String images;
}
