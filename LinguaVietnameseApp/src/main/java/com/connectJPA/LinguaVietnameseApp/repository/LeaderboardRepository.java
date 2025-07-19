package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, String> {
    Optional<Leaderboard> findTopByTabAndPeriodOrderBySnapshotDateDesc(String tab, String period);

    Optional<Leaderboard> findByTabAndPeriodAndSnapshotDate(String tab, String period, LocalDate snapshotDate);

}