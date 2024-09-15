package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.request.DrinksUpdateRequest;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.service.DrinksService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrinksController {
    DrinksService drinksService;

    @PostMapping("/create")
    public ApiResponse<DrinksResponse> createDrinks(@RequestBody @Valid DrinksCreationRequest request) {
        return ApiResponse.<DrinksResponse>builder()
                .result(drinksService.createProduct(request))
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<DrinksResponse>> getDrinks() {
        return ApiResponse.<List<DrinksResponse>>builder()
                .result(drinksService.getAllProducts())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<DrinksResponse>> searchDrinks(@RequestParam("keyword") String keyword) {
        return ApiResponse.<List<DrinksResponse>>builder()
                .result(drinksService.searchProducts(keyword))
                .build();
    }

    @GetMapping("/get-by-name")
    public ApiResponse<DrinksResponse> getDrinksByName(@RequestParam("name") String name) {
        return ApiResponse.<DrinksResponse>builder()
                .result(drinksService.getProductByName(name))
                .build();
    }

    @GetMapping("/get-by-type")
    public ApiResponse<List<DrinksResponse>> getDrinksByType(@RequestParam("type") String type) {
        return ApiResponse.<List<DrinksResponse>>builder()
                .result(drinksService.getProductsByType(type))
                .build();
    }

    @GetMapping("/get-by-price")
    public ApiResponse<List<DrinksResponse>> getDrinksByPriceBetween(@RequestParam("priceMin") BigDecimal priceMin,
                                                                     @RequestParam("priceMax") BigDecimal priceMax) {
        return ApiResponse.<List<DrinksResponse>>builder()
                .result(drinksService.getProductsByPrice(priceMin, priceMax))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable("Id") String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(drinksService.getProductById(id))
                .build();
    }

    @GetMapping("/get-by-rating")
    public ApiResponse<List<DrinksResponse>> getDrinksByRating(@RequestParam("ratingMin") double ratingMin,
                                                               @RequestParam("ratingMax") double ratingMax) {
        return ApiResponse.<List<DrinksResponse>>builder()
                .result(drinksService.getProductsByRating(ratingMin, ratingMax))
                .build();
    }

    @PutMapping("/{drinksId}")
    public ApiResponse<DrinksResponse> updateDrinks(@PathVariable("drinksId") String drinksId, @RequestBody DrinksUpdateRequest request) {
        return ApiResponse.<DrinksResponse>builder()
                .result(drinksService.updateProduct(drinksId, request))
                .build();
    }

    @DeleteMapping("/{drinksId}")
    public ApiResponse<String> deleteDrinks(@PathVariable String drinksId) {
        drinksService.deleteProduct(drinksId);
        return ApiResponse.<String>builder()
                .result("Drinks has been deleted")
                .build();
    }
}
