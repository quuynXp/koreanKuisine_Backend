package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.UserBadge;
import com.connectJPA.LinguaVietnameseApp.entity.id.UserBadgeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepository extends JpaRepository<UserBadge, UserBadgeId> {
    // Tìm theo userId trong embeddedId

    // Tìm theo badgeId trong embeddedId
    List<UserBadge> findByUserBadgeIdBadgeName(String badgeName);

    // Tìm theo cả 2
    Optional<UserBadge> findByUserBadgeIdUserIdAndUserBadgeIdBadgeName(String userId, String badgeName);
    List<UserBadge> findByUserBadgeIdUserId(String userId);
}

