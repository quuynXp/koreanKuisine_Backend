package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/products")
    public List<ProductResponse> getRecommendedProducts(@RequestParam String userId) {
        return recommendationService.getRecommendedProducts(userId);
    }
}
