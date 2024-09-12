package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.DishCreationRequest;
import com.connectJPA.demo.dto.request.DishUpdateRequest;
import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    DishResponse toDishResponse(Dish dish);

    Dish toDish(DishCreationRequest request);


    void updateDish(@MappingTarget Dish dish, DishUpdateRequest request);
}



