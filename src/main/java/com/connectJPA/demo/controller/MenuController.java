package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.response.*;
import com.connectJPA.demo.entity.Product;
import com.connectJPA.demo.repository.ProductRepository;
import com.connectJPA.demo.service.DishService;
import com.connectJPA.demo.service.DrinksService;
import com.connectJPA.demo.mapper.MenuMapper;
import com.connectJPA.demo.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuController {
    DishService dishService;
    DrinksService drinksService;
    MenuMapper menuMapper;


    @GetMapping("/filter")
    public ApiResponse<List<MenuResponse>> getFilteredAndSortedProducts(
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {


        List<MenuResponse> filteredMenu = new ArrayList<>();

        if ("dish".equalsIgnoreCase(productType)) {
            List<DishResponse> dishes = dishService.filterProducts(type, description, minPrice, maxPrice, minRating, maxRating);
            filteredMenu.addAll(dishes.stream()
                    .map(menuMapper::toMenuResponse)
                    .toList());
        } else if ("drinks".equalsIgnoreCase(productType)) {
            List<DrinksResponse> drinks = drinksService.filterProducts(type, description, minPrice, maxPrice, minRating, maxRating);
            filteredMenu.addAll(drinks.stream()
                    .map(menuMapper::toMenuResponse)
                    .toList());
        } else {
            List<DishResponse> allDishes = dishService.filterProducts(type, description, minPrice, maxPrice, minRating, maxRating);
            List<DrinksResponse> allDrinks = drinksService.filterProducts(type, description, minPrice, maxPrice, minRating, maxRating);
            filteredMenu.addAll(allDishes.stream()
                    .map(menuMapper::toMenuResponse)
                    .toList());
            filteredMenu.addAll(allDrinks.stream()
                    .map(menuMapper::toMenuResponse)
                    .toList());
        }

        return ApiResponse.<List<MenuResponse>>builder()
                .result(filteredMenu)
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<MenuResponse>> getMenu() {
        List<MenuResponse> combinedMenu = new ArrayList<>();

        combinedMenu.addAll(dishService.getAllProducts().stream()
                .map(menuMapper::toMenuResponse)
                .toList());

        combinedMenu.addAll(drinksService.getAllProducts().stream()
                .map(menuMapper::toMenuResponse)
                .toList());

        return ApiResponse.<List<MenuResponse>>builder()
                .result(combinedMenu)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<MenuResponse>> search(@RequestParam("keyword") String keyword) {
        List<MenuResponse> combinedSearchResults = new ArrayList<>();

        combinedSearchResults.addAll(dishService.searchProducts(keyword).stream()
                .map(menuMapper::toMenuResponse)
                .toList());

        combinedSearchResults.addAll(drinksService.searchProducts(keyword).stream()
                .map(menuMapper::toMenuResponse)
                .toList());

        return ApiResponse.<List<MenuResponse>>builder()
                .result(combinedSearchResults)
                .build();
    }

    @GetMapping("/{productType}/{id}")
    public ResponseEntity<ApiResponse<MenuResponse>> getProductById(
            @PathVariable String productType,
            @PathVariable String id) {

        MenuResponse product = null;

        switch (productType.toLowerCase()) {
            case "dish":
                DishResponse dish = dishService.getDishById(id);
                if (dish != null) {
                    product = menuMapper.toMenuResponse(dish);
                }
                break;
            case "drinks":
                DrinksResponse drink = drinksService.getDrinksById(id);
                if (drink != null) {
                    product = menuMapper.toMenuResponse(drink);
                }
                break;
            default:
                return ResponseEntity.badRequest().body(
                        ApiResponse.<MenuResponse>builder()
                                .message("Invalid product type")
                                .build());
        }

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                ApiResponse.<MenuResponse>builder()
                        .result(product)
                        .build());
    }


}
