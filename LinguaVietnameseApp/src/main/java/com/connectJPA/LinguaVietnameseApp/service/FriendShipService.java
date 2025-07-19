package com.connectJPA.LinguaVietnameseApp.service;


import com.connectJPA.LinguaVietnameseApp.dto.FriendShipDTO;
import com.connectJPA.LinguaVietnameseApp.entity.FriendRequest;
import com.connectJPA.LinguaVietnameseApp.entity.Notification;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.enums.FriendRequestStatus;
import com.connectJPA.LinguaVietnameseApp.mapper.FriendShipMapper;
import com.connectJPA.LinguaVietnameseApp.repository.FriendRequestRepository;
import com.connectJPA.LinguaVietnameseApp.repository.FriendShipRepository;
import com.connectJPA.LinguaVietnameseApp.repository.NotificationRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.connectJPA.LinguaVietnameseApp.entity.FriendRequest.FriendshipStatus.PENDING;

@Service
@RequiredArgsConstructor
public class FriendShipService {
    private final FriendRequestRepository friendRequestRepo;
    private final UserRepository userRepo;
    private final NotificationRepository notificationRepo;
    private final FirebaseService firebaseService;
    private final FriendShipRepository friendRepository;
    private final FriendShipMapper friendMapper;

    public List<FriendShipDTO> getByRequester(String userId) {
        return friendRepository.findByFriendshipIdRequesterId(userId).stream().map(friendMapper::toDto).toList();
    }

    public void sendFriendRequest(String senderId, String receiverId) {
        User requester = userRepo.findById(senderId).orElseThrow();
        User receiver = userRepo.findById(receiverId).orElseThrow();

        if (friendRequestRepo.existsByRequesterIdAndReceiverId(requester.getUserId(), receiver.getUserId())) {
            throw new RuntimeException("Đã gửi lời mời trước đó");
        }

        FriendRequest request = FriendRequest.builder()
                .requesterId(requester.getUserId())
                .receiverId(receiver.getUserId())
                .status(PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        friendRequestRepo.save(request);

        // Gửi Notification
        Notification noti = Notification.builder()
                .userId(receiver.getUserId())
                .title("Bạn có lời mời kết bạn mới")
                .content("Người dùng " + requester.getFullname() + " đã gửi lời mời kết bạn")
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepo.save(noti);

        // Đẩy push notification
        firebaseService.sendNotificationToUser(receiverId, noti.getTitle(), noti.getContent());
    }

    @Cacheable(value = "friends", key = "#userId")
    public List<FriendShipDTO> getByUser(String userId) {
        return friendRepository.findByFriendshipIdRequesterIdOrFriendshipIdReceiverId(userId, userId)
                .stream().map(friendMapper::toDto).toList();
    }

    @Caching(evict = {@CacheEvict(value = "friends", key = "#dto.requesterId"), @CacheEvict(value = "friends", key = "#dto.receiverId")})
    public FriendShipDTO create(FriendShipDTO dto) {
        return friendMapper.toDto(friendRepository.save(friendMapper.toEntity(dto)));
    }

    @Caching(evict = {
            @CacheEvict(value = "friends", key = "#requesterId"),
            @CacheEvict(value = "friends", key = "#receiverId")
    })
    public void delete(String requesterId, String receiverId) {
        friendRepository.deleteByFriendshipIdRequesterIdAndFriendshipIdReceiverId(requesterId, receiverId);
    }

}
