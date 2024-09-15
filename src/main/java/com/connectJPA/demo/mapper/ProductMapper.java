package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.DishCreationRequest;
import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.Drinks;
import com.connectJPA.demo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    DishResponse toDishResponse(Dish dish);

    Dish toDish(DishCreationRequest request);

    DrinksResponse toDrinksResponse(Drinks drinks);

    Drinks toDrinks(DrinksCreationRequest request);

    Dish copyDish(Dish source);

    Drinks copyDrinks(Drinks source);
}
