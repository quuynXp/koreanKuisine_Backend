package com.connectJPA.demo.entity;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.math.BigDecimal;

@Data
@MappedSuperclass
public abstract class Product {
    @Id
    private String id;

    private String name;
    private BigDecimal price;
    private String ingredients;
    private String type;
    private String description;
    private double rating;
    private String imageUrl;
    private String images;

}


