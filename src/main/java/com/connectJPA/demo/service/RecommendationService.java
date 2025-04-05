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
import com.connectJPA.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationService {
    private final ProductRepository productRepository;
    OrderRepository orderRepository;
    DishRepository dishRepository;
    DrinksRepository drinksRepository;
    DishMapper dishMapper;
    DrinksMapper drinksMapper;


    public List<ProductResponse> getRecommendedProducts(String userId) {
        List<Orders> pastOrders = orderRepository.findByUserId(userId);

        List<String> productIds = pastOrders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .map(OrderDetail::getProductId)
                .distinct()
                .toList();
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) productRepository.findAllById(productIds);


        Map<String, Product> productMap = products.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Product::getId,
                        product -> product,
                        (existing, duplicate) -> existing
                ));


        Map<Product, Long> productFrequency = pastOrders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .collect(Collectors.groupingBy(
                        orderDetail -> productMap.get(orderDetail.getProductId()),
                        Collectors.counting()
                ));


        List<Product> topProducts = productFrequency.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();

        List<Dish> topDishes = topProducts.stream()
                .filter(product -> "dish".equals(product.getType()))
                .map(product -> (Dish) product)
                .toList();

        List<Drinks> topDrinks = topProducts.stream()
                .filter(product -> "drink".equals(product.getType()))
                .map(product -> (Drinks) product)
                .toList();

        List<String> dishCategories = topDishes.stream().map(Dish::getType).collect(Collectors.toList());
        List<String> drinkCategories = topDrinks.stream().map(Drinks::getType).collect(Collectors.toList());

        List<Dish> suggestedDishes = dishRepository.findByTypeIn(dishCategories).stream()
                .filter(dish -> !topDishes.contains(dish))
                .limit(5)
                .toList();

        List<Drinks> suggestedDrinks = drinksRepository.findByTypeIn(drinkCategories).stream()
                .filter(drink -> !topDrinks.contains(drink))
                .limit(5)
                .toList();

        List<Dish> finalDishRecommendations = new ArrayList<>(topDishes);
        finalDishRecommendations.addAll(suggestedDishes);

        List<Drinks> finalDrinksRecommendations = new ArrayList<>(topDrinks);
        finalDrinksRecommendations.addAll(suggestedDrinks);

        List<DishResponse> dishResponses = dishMapper.toDishResponse(finalDishRecommendations);
        List<DrinksResponse> drinkResponses = drinksMapper.toDrinksResponse(finalDrinksRecommendations);

        return Stream.concat(dishResponses.stream(), drinkResponses.stream()).collect(Collectors.toList());
    }

}
