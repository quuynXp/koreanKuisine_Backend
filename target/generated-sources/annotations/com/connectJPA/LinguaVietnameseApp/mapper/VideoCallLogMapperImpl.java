package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallLogDTO;
import com.connectJPA.LinguaVietnameseApp.entity.VideoCallLog;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class VideoCallLogMapperImpl implements VideoCallLogMapper {

    @Override
    public VideoCallLogDTO toDto(VideoCallLog entity) {
        if ( entity == null ) {
            return null;
        }

        VideoCallLogDTO.VideoCallLogDTOBuilder videoCallLogDTO = VideoCallLogDTO.builder();

        if ( entity.getVideoCallId() != null ) {
            videoCallLogDTO.videoCallId( entity.getVideoCallId().toString() );
        }
        if ( entity.getCallerId() != null ) {
            videoCallLogDTO.callerId( entity.getCallerId().toString() );
        }
        if ( entity.getCalleeId() != null ) {
            videoCallLogDTO.calleeId( entity.getCalleeId().toString() );
        }
        if ( entity.getRoomId() != null ) {
            videoCallLogDTO.roomId( entity.getRoomId().toString() );
        }
        videoCallLogDTO.createdAt( entity.getCreatedAt() );
        videoCallLogDTO.updatedAt( entity.getUpdatedAt() );

        return videoCallLogDTO.build();
    }

    @Override
    public VideoCallLog toEntity(VideoCallLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        VideoCallLog.VideoCallLogBuilder<?, ?> videoCallLog = VideoCallLog.builder();

        videoCallLog.createdAt( dto.getCreatedAt() );
        videoCallLog.updatedAt( dto.getUpdatedAt() );
        if ( dto.getVideoCallId() != null ) {
            videoCallLog.videoCallId( UUID.fromString( dto.getVideoCallId() ) );
        }
        if ( dto.getRoomId() != null ) {
            videoCallLog.roomId( UUID.fromString( dto.getRoomId() ) );
        }
        if ( dto.getCallerId() != null ) {
            videoCallLog.callerId( UUID.fromString( dto.getCallerId() ) );
        }
        if ( dto.getCalleeId() != null ) {
            videoCallLog.calleeId( UUID.fromString( dto.getCalleeId() ) );
        }

        return videoCallLog.build();
    }
}
