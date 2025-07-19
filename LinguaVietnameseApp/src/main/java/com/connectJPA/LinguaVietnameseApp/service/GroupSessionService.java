package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.GroupSessionDTO;
import com.connectJPA.LinguaVietnameseApp.entity.GroupSession;
import com.connectJPA.LinguaVietnameseApp.mapper.GroupSessionMapper;
import com.connectJPA.LinguaVietnameseApp.repository.GroupSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupSessionService {
    private final GroupSessionRepository sessionRepository;
    private final GroupSessionMapper sessionMapper;

    public List<GroupSessionDTO> getByRoomId(String roomId) {
        return sessionRepository.findByRoomId(roomId).stream().map(sessionMapper::toDto).toList();
    }
    public List<GroupSessionDTO> getByUserId(String userId) {
        return sessionRepository.findByUserId(userId).stream().map(sessionMapper::toDto).toList();
    }

    public GroupSessionDTO create(GroupSessionDTO dto) {
        GroupSession entity = sessionMapper.toEntity(dto);
        return sessionMapper.toDto(sessionRepository.save(entity));
    }

    public Optional<GroupSessionDTO> update(String id, GroupSessionDTO dto) {
        return sessionRepository.findById(id).map(session -> {
            session.setGroupSessionId(dto.getLessonId());
            session.setStartedAt(dto.getStartedAt());
            session.setEndedAt(dto.getEndedAt());
            return sessionMapper.toDto(sessionRepository.save(session));
        });
    }

    public void delete(String id) {
        sessionRepository.deleteById(id);
    }
}
