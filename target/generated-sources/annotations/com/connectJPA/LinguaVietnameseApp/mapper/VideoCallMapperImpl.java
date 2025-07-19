package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallDTO;
import com.connectJPA.LinguaVietnameseApp.entity.VideoCall;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class VideoCallMapperImpl implements VideoCallMapper {

    @Override
    public VideoCallDTO toDto(VideoCall entity) {
        if ( entity == null ) {
            return null;
        }

        VideoCallDTO.VideoCallDTOBuilder videoCallDTO = VideoCallDTO.builder();

        if ( entity.getVideoCallId() != null ) {
            videoCallDTO.videoCallId( entity.getVideoCallId().toString() );
        }
        if ( entity.getRoomId() != null ) {
            videoCallDTO.roomId( entity.getRoomId().toString() );
        }
        videoCallDTO.createdAt( entity.getCreatedAt() );
        videoCallDTO.updatedAt( entity.getUpdatedAt() );
        if ( entity.getIsDeleted() != null ) {
            videoCallDTO.isDeleted( entity.getIsDeleted() );
        }

        return videoCallDTO.build();
    }

    @Override
    public VideoCall toEntity(VideoCallDTO dto) {
        if ( dto == null ) {
            return null;
        }

        VideoCall.VideoCallBuilder<?, ?> videoCall = VideoCall.builder();

        if ( dto.getVideoCallId() != null ) {
            videoCall.videoCallId( UUID.fromString( dto.getVideoCallId() ) );
        }
        if ( dto.getRoomId() != null ) {
            videoCall.roomId( UUID.fromString( dto.getRoomId() ) );
        }
        videoCall.createdAt( dto.getCreatedAt() );
        videoCall.updatedAt( dto.getUpdatedAt() );

        return videoCall.build();
    }
}
