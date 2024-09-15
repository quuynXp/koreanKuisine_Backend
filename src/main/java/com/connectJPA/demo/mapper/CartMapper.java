package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.CartRequest;
import com.connectJPA.demo.dto.response.CartResponse;
import com.connectJPA.demo.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderDetails", target = "items")
    CartResponse toCartResponse(Cart cart);

    Cart toCart(CartRequest cartRequest);
}
