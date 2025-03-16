package com.connectJPA.demo.service;

import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.entity.*;
import com.connectJPA.demo.mapper.DishMapper;
import com.connectJPA.demo.mapper.DrinksMapper;
import com.connectJPA.demo.repository.DishRepository;
import com.connectJPA.demo.repository.DrinksRepository;
import com.connectJPA.demo.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationService {
    OrderRepository orderRepository;
    DishRepository dishRepository;
    DrinksRepository drinksRepository;
    DishMapper dishMapper;
    DrinksMapper drinksMapper;


    public List<ProductResponse> getRecommendedProducts(String userId) {
        List<Orders> pastOrders = orderRepository.findByUserId(userId);

        // Nhóm sản phẩm theo tần suất đặt hàng
        Map<Product, Long> productFrequency = pastOrders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .collect(Collectors.groupingBy(OrderDetail::getProduct, Collectors.counting()));

        // Lấy top 5 sản phẩm phổ biến
        List<Product> topProducts = productFrequency.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();

        // Lọc danh sách theo loại sản phẩm (Dish hoặc Drinks)
        List<Dish> topDishes = topProducts.stream()
                .filter(product -> "dish".equals(product.getType()))
                .map(product -> (Dish) product)
                .toList();

        List<Drinks> topDrinks = topProducts.stream()
                .filter(product -> "drink".equals(product.getType()))
                .map(product -> (Drinks) product)
                .toList();

        // Gợi ý thêm món cùng loại nhưng chưa từng đặt
        Set<String> dishCategories = topDishes.stream().map(Dish::getType).collect(Collectors.toSet());
        Set<String> drinkCategories = topDrinks.stream().map(Drinks::getType).collect(Collectors.toSet());

        List<Dish> suggestedDishes = dishRepository.findByTypeIn(String.valueOf(dishCategories)).stream()
                .filter(dish -> !topDishes.contains(dish))
                .limit(5)
                .toList();
        List<Drinks> suggestedDrinks = drinksRepository.findByTypeIn(String.valueOf(drinkCategories)).stream()
                .filter(drink -> !topDrinks.contains(drink))
                .limit(5)
                .toList();

        // Gộp danh sách
        List<Dish> finalDishRecommendations = new ArrayList<>(topDishes);
        finalDishRecommendations.addAll(suggestedDishes);

        List<Drinks> finalDrinksRecommendations = new ArrayList<>(topDrinks);
        finalDrinksRecommendations.addAll(suggestedDrinks);

        // Convert sang Response
        List<DishResponse> dishResponses = dishMapper.toDishResponse(finalDishRecommendations);
        List<DrinksResponse> drinkResponses = drinksMapper.toDrinksResponse(finalDrinksRecommendations);

        // Trả về danh sách gợi ý
        return Stream.concat(dishResponses.stream(), drinkResponses.stream()).collect(Collectors.toList());
    }

}
