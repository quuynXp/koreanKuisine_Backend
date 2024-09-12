package com.connectJPA.demo.service;

import com.connectJPA.demo.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService<CreateRequest, UpdateRequest, Response> {
    Response createProduct(CreateRequest request);
    Response updateProduct(String productId, UpdateRequest request);
    ProductResponse getProductById(String productId);
    List<Response> filterProducts(String type, String basicInfo, BigDecimal minPrice, BigDecimal maxPrice, Double minRating, Double maxRating);
    List<Response> getAllProducts();
    Response getProductByName(String name);
    List<Response> searchProductsByDescription(String keyword);
    List<Response> searchProducts(String keyword);
    List<Response> getProductsByType(String type);
    List<Response> getProductsByPrice(BigDecimal priceMin, BigDecimal priceMax);
    List<Response> getProductsByRating(double ratingMin, double ratingMax);
    void deleteProduct(String id);
}


