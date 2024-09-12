package com.connectJPA.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "drinks")
public class Drinks extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String name;

    BigDecimal price;
    String ingredients;
    String description;
    String type;
    double rating;

    @Column(name = "image_Url")
    String imageUrl;

    @Column(columnDefinition = "JSON")
    @Lob
    String images;

}
