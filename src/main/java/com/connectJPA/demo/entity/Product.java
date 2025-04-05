package com.connectJPA.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;



@Getter
@Setter
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


