package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.request.DrinksUpdateRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.Drinks;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrinksMapper {
    DrinksMapper INSTANCE = Mappers.getMapper(DrinksMapper.class);

    DrinksResponse toDrinksResponse(Drinks drinks);

    Drinks toDrinks(DrinksCreationRequest request);

    List<DrinksResponse> toDrinksResponse(List<Drinks> topDrinks);


    void updateDrinks(@MappingTarget Drinks drinks, DrinksUpdateRequest request);
}



