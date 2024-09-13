package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.dto.response.MenuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "type", target = "productType")
    @Mapping(target = "type", constant = "dish")
    MenuResponse toMenuResponse(DishResponse dishResponse);

    @Mapping(source = "type", target = "productType")
    @Mapping(target = "type", constant = "drinks")
    MenuResponse toMenuResponse(DrinksResponse drinksResponse);
}




