package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "leaderboards_entries")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "leaderboard_entry_id")
    private UUID leaderboardEntryId;

    @Column(name = "leaderboard_id")
    private UUID leaderboardId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "streak", nullable = false)
    private Integer streak;

    @Column(name = "change", nullable = false)
    private Integer change;

}

