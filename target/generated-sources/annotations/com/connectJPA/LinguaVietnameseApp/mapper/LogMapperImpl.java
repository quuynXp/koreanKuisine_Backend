package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LogDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Log;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LogMapperImpl implements LogMapper {

    @Override
    public LogDTO toDto(Log entity) {
        if ( entity == null ) {
            return null;
        }

        LogDTO.LogDTOBuilder logDTO = LogDTO.builder();

        logDTO.logId( entity.getLogId() );
        logDTO.action( entity.getAction() );
        logDTO.targetTable( entity.getTargetTable() );
        logDTO.targetId( entity.getTargetId() );
        logDTO.description( entity.getDescription() );
        logDTO.userId( entity.getUserId() );
        logDTO.createdAt( entity.getCreatedAt() );
        logDTO.updatedAt( entity.getUpdatedAt() );

        return logDTO.build();
    }

    @Override
    public Log toEntity(LogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Log.LogBuilder<?, ?> log = Log.builder();

        log.createdAt( dto.getCreatedAt() );
        log.updatedAt( dto.getUpdatedAt() );
        log.logId( dto.getLogId() );
        log.action( dto.getAction() );
        log.targetTable( dto.getTargetTable() );
        log.targetId( dto.getTargetId() );
        log.description( dto.getDescription() );
        log.userId( dto.getUserId() );

        return log.build();
    }
}
