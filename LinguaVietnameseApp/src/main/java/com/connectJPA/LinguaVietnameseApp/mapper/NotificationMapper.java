package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.NotificationDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDto(Notification entity);
    Notification toEntity(NotificationDTO dto);
}
