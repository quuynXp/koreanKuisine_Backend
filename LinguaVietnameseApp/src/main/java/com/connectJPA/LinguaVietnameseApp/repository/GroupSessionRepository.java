package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.GroupSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupSessionRepository extends JpaRepository<GroupSession, String> {
    List<GroupSession> findByRoomId(String roomId);
    List<GroupSession> findByUserId(String userId);
}

