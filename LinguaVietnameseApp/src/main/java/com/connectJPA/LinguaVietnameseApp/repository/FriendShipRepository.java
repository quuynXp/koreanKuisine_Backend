package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.Friendship;
import com.connectJPA.LinguaVietnameseApp.entity.id.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendShipRepository extends JpaRepository<Friendship, FriendshipId> {
    List<Friendship> findByFriendshipIdRequesterId(String requesterId);
    List<Friendship> findByFriendshipIdReceiverId(String receiverId);
    void deleteByFriendshipIdRequesterIdAndFriendshipIdReceiverId(String requesterId, String receiverId);
    List<Friendship> findByFriendshipIdRequesterIdOrFriendshipIdReceiverId(String requesterId, String friendId);
}
