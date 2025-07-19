package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, String> {
    boolean existsByRequesterIdAndReceiverId(UUID requesterId, UUID receiverId);
}
