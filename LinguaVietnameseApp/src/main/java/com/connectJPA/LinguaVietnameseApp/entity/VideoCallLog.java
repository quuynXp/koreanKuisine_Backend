package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video_call_logs")
@SuperBuilder
public class VideoCallLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "video_call_id")
    private UUID videoCallId;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "caller_id")
    private UUID callerId;

    @Column(name = "callee_id")
    private UUID calleeId;

    @Column(name = "video_call_type")
    private Integer videoCallType;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;
}
