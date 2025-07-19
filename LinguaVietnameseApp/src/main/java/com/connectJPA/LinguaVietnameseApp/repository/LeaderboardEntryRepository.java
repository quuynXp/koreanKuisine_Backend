package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.LeaderboardEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, String> {
    List<LeaderboardEntry> findTop3ByLeaderboardIdOrderByRankAsc(String leaderboardId);

    Page<LeaderboardEntry> findByLeaderboardIdOrderByRankAsc(String leaderboardId, Pageable pageable);

    Optional<LeaderboardEntry> findByLeaderboardIdAndUserId(String leaderboardId, String userId);

    List<LeaderboardEntry> findByLeaderboardId(String leaderboardId);

    List<LeaderboardEntry> findByUserId(String userId);

    List<LeaderboardEntry> findByLeaderboardIdAndUserIdIn(String leaderboardId, List<String> userIds);
}