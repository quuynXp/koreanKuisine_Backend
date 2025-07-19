package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.VideoCallParticipantId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_call_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoCallParticipant extends BaseEntity {

    @EmbeddedId
    private VideoCallParticipantId id;
}
