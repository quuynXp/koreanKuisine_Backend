package com.connectJPA.LinguaVietnameseApp.mapper;


import com.connectJPA.LinguaVietnameseApp.dto.FriendShipDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Friendship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendShipMapper {
    FriendShipDTO toDto(Friendship entity);
    Friendship toEntity(FriendShipDTO dto);
}
