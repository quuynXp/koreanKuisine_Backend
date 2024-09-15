package com.connectJPA.demo.dto.response;

import java.math.BigDecimal;

public interface ProductResponse {
    String getId();
    String getName();
    BigDecimal getPrice();
    String getIngredients();
    String getDescription();
    String getType();
    double getRating();
    String getImageUrl();
    String getImages();
}
