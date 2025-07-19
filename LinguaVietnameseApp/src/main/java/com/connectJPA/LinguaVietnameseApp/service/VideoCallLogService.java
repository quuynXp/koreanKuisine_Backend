package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallLogDTO;
import com.connectJPA.LinguaVietnameseApp.mapper.VideoCallLogMapper;
import com.connectJPA.LinguaVietnameseApp.repository.VideoCallLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VideoCallLogService {
    private final VideoCallLogRepository callRepository;
    private final VideoCallLogMapper callMapper;

    public List<VideoCallLogDTO> getByCaller(String callerId) {
        return callRepository.findByCallerId(callerId).stream().map(callMapper::toDto).toList();
    }
    public List<VideoCallLogDTO> getByUser(String userId) {
        return callRepository.findByCallerIdOrCalleeId(userId, userId)
                .stream().map(callMapper::toDto).toList();
    }

    public VideoCallLogDTO create(VideoCallLogDTO dto) {
        return callMapper.toDto(callRepository.save(callMapper.toEntity(dto)));
    }
}
