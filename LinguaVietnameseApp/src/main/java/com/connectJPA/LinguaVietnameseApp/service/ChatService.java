package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.entity.ChatMessage;
import com.connectJPA.LinguaVietnameseApp.entity.Room;
import com.connectJPA.LinguaVietnameseApp.enums.RoomType;
import com.connectJPA.LinguaVietnameseApp.repository.MessageRepository;
import com.connectJPA.LinguaVietnameseApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final NotificationService notificationService;
    private final ChatAiService chatAiService;
    private final AiRateLimiter aiRateLimiter;

    public void handleSendMessage(ChatMessage chatMessage) {
        // 1. Lưu tin nhắn user
        ChatMessage savedMessage = messageService.save(chatMessage);

        // 2. Gửi tin nhắn đó về lại room
        messagingTemplate.convertAndSend("/topic/room." + savedMessage.getRoomId(), savedMessage);

        // 3. Nếu là phòng AI thì xử lý riêng
        Room room = roomRepository.findById(savedMessage.getRoomId()).orElse(null);
        if (room != null && room.getRoomType() == RoomType.AI_CHAT) {
            // Từ chối nếu gửi quá nhanh
            if (!aiRateLimiter.allowRequest(savedMessage.getSenderId())) return;

            // Gọi AI, tạo response như message thật
            ChatMessage aiReply = chatAiService.chatWithAi(savedMessage.getSenderId(), room, savedMessage.getContent());

            // Gửi trả lời AI qua WebSocket
            messagingTemplate.convertAndSend("/topic/room." + room.getRoomId(), aiReply);
        }

        // 4. Push FCM cho người còn lại trong phòng
        notificationService.pushToUsersInRoom(savedMessage);
    }
}
