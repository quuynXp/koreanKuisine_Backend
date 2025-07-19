package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.entity.ChatMessage;
import com.connectJPA.LinguaVietnameseApp.entity.Notification;
import com.connectJPA.LinguaVietnameseApp.entity.RoomMember;
import com.connectJPA.LinguaVietnameseApp.repository.NotificationRepository;
import com.connectJPA.LinguaVietnameseApp.repository.RoomMemberRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserFcmTokenRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RoomMemberRepository roomMemberRepository;

    private UserFcmTokenRepository userFcmTokenRepo;

    public void pushToUsersInRoom(ChatMessage message) {
        List<RoomMember> members = roomMemberRepository.findByIdRoomId(message.getRoomId());

        for (RoomMember member : members) {
            String receiverId = String.valueOf(member.getRoomMemberId());

            if (!receiverId.equals(message.getSenderId())) {
                List<String> tokens = userFcmTokenRepo.findFcmTokensByUserId(receiverId);
                if (!tokens.isEmpty()) {
                    firebaseService.sendNotificationToUser(
                            String.valueOf(member.getMemberId()),
                            "New message from " + (userRepository.findById(message.getSenderId())).get().getFullname(),
                            message.getContent()
                    );
                }
            }
        }
    }

    public void markAsRead(String userId) {
        List<Notification> notification =  notificationRepository.findByUserIdAndIsReadFalse(userId);
        for (Notification notification1 : notification) {
            notification1.setRead(true);
            notificationRepository.save(notification1);
        }
    }

    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }
}

