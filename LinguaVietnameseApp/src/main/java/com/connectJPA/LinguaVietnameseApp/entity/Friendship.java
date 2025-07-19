package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.FriendshipId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Friendship extends BaseEntity {
    @EmbeddedId
    private FriendshipId friendshipId;


    @Enumerated(EnumType.STRING)
    private FriendRequest.FriendshipStatus status; // "pending", "accepted", "blocked"

}
