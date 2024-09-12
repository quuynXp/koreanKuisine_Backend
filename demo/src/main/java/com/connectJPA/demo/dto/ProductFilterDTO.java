package com.connectJPA.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilterDTO {
    private String searchTerm;
    private List<String> types;
    private Double minPrice;
    private Double maxPrice;
    private Integer minRating;
    private Integer maxRating;
    private String sortBy;
    private boolean ascending;

}
