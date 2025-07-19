package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.NotificationDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Notification;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDto(Notification entity) {
        if ( entity == null ) {
            return null;
        }

        NotificationDTO.NotificationDTOBuilder notificationDTO = NotificationDTO.builder();

        if ( entity.getNotificationId() != null ) {
            notificationDTO.notificationId( entity.getNotificationId().toString() );
        }
        notificationDTO.type( entity.getType() );
        if ( entity.getUserId() != null ) {
            notificationDTO.userId( entity.getUserId().toString() );
        }
        notificationDTO.createdAt( entity.getCreatedAt() );
        notificationDTO.updatedAt( entity.getUpdatedAt() );

        return notificationDTO.build();
    }

    @Override
    public Notification toEntity(NotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Notification.NotificationBuilder<?, ?> notification = Notification.builder();

        notification.createdAt( dto.getCreatedAt() );
        notification.updatedAt( dto.getUpdatedAt() );
        if ( dto.getNotificationId() != null ) {
            notification.notificationId( UUID.fromString( dto.getNotificationId() ) );
        }
        if ( dto.getUserId() != null ) {
            notification.userId( UUID.fromString( dto.getUserId() ) );
        }
        notification.type( dto.getType() );
        notification.read( dto.isRead() );

        return notification.build();
    }
}
