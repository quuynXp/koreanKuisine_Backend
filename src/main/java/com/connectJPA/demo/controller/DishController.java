package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.request.DishCreationRequest;
import com.connectJPA.demo.dto.request.DishUpdateRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.service.DishService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DishController {
    DishService dishService;

    @PostMapping("/create")
    public ApiResponse<DishResponse> createDish(@RequestBody DishCreationRequest request) {
        return ApiResponse.<DishResponse>builder()
                .result(dishService.createProduct(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable("Id") String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(dishService.getProductById(id))
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<DishResponse>> getDish() {
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getAllProducts())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<DishResponse>> searchDish(@RequestParam("keyword") String keyword) {
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.searchProducts(keyword))
                .build();
    }

    @GetMapping("/get-by-name")
    public ApiResponse<DishResponse> getDishByName(@RequestParam("name") String name) {
        return ApiResponse.<DishResponse>builder()
                .result(dishService.getProductByName(name))
                .build();
    }

    @GetMapping("/get-by-type")
    public ApiResponse<List<DishResponse>> getDishByType(@RequestParam("type") String type) {
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getProductsByType(type))
                .build();
    }

    @GetMapping("/get-by-price")
    public ApiResponse<List<DishResponse>> getDishByPriceBetween(@RequestParam("priceMin") BigDecimal priceMin,
                                                                 @RequestParam("priceMax") BigDecimal priceMax) {
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getProductsByPrice(priceMin, priceMax))
                .build();
    }

    @GetMapping("/get-by-rating")
    public ApiResponse<List<DishResponse>> getDishByRating(@RequestParam("ratingMin") double ratingMin,
                                                           @RequestParam("ratingMax") double ratingMax) {
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getProductsByRating(ratingMin, ratingMax))
                .build();
    }

    @PutMapping("/{dishId}")
    public ApiResponse<DishResponse> updateDish(@PathVariable("dishId") String dishId, @RequestBody DishUpdateRequest request) {
        return ApiResponse.<DishResponse>builder()
                .result(dishService.updateProduct(dishId, request))
                .build();
    }

    @DeleteMapping("/{dishId}")
    public ApiResponse<String> deleteDish(@PathVariable String dishId) {
        dishService.deleteProduct(dishId);
        return ApiResponse.<String>builder()
                .result("Dish has been deleted")
                .build();
    }
}
