package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.VideoCallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCallLogRepository extends JpaRepository<VideoCallLog, String> {
    List<VideoCallLog> findByCallerId(String callerId);
    List<VideoCallLog> findByRoomId(String roomId);
    List<VideoCallLog> findByCallerIdOrCalleeId(String callerId, String calleeId);
}
