package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.UserBadgeDTO;
import com.connectJPA.LinguaVietnameseApp.entity.UserBadge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBadgeMapper {
    UserBadgeDTO toDto(UserBadge entity);
    UserBadge toEntity(UserBadgeDTO dto);
}
