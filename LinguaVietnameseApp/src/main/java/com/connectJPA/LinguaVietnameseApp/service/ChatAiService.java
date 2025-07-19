package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.entity.*;
import com.connectJPA.LinguaVietnameseApp.enums.RoomType;
import com.connectJPA.LinguaVietnameseApp.repository.ChatMessageRepository;
import com.connectJPA.LinguaVietnameseApp.repository.RoomRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatAiService {

    private final ChatMessageRepository chatMessageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Value("${huggingface.api.key}")
    private String huggingFaceApiKey;

    private final String MISTRAL_URL = "https://api-inference.huggingface.co/models/mistralai/Mixtral-8x7B-Instruct-v0.1";


    @Cacheable(value = "aiReplies", key = "#userInput")
    @Transactional
    public ChatMessage chatWithAi(String senderId, Room room, String userMessage) {
        if (!(room.getRoomType() == RoomType.AI_CHAT)) {
            throw new IllegalArgumentException("This room is not an AI chat room.");
        }

        // Gửi message của user
        ChatMessage userMsg = ChatMessage.builder()
                .roomId(room.getRoomId())
                .senderId(senderId)
                .content(userMessage)
                .messageType(ChatMessage.MessageType.TEXT)
                .createdAt(LocalDateTime.now())
                .isRead(true)
                .build();
        chatMessageRepository.save(userMsg);

        // Gọi OpenAI
        String aiResponse = callMistral(userMessage);

        // Tạo message AI
        ChatMessage aiMsg = ChatMessage.builder()
                .roomId(room.getRoomId())
                .senderId(null) // hoặc user với id = "AI"
                .content(aiResponse)
                .messageType(ChatMessage.MessageType.TEXT)
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .build();

        return chatMessageRepository.save(aiMsg);
    }



    private String callMistral(String userInput) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> payload = Map.of(
                "inputs", "User: " + userInput + "\nAI:",
                "parameters", Map.of(
                        "max_new_tokens", 100,
                        "temperature", 0.7
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + huggingFaceApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(MISTRAL_URL, request, Map.class);
            List<Map<String, Object>> result = (List<Map<String, Object>>) response.getBody().get("generated_texts");

            if (result != null && !result.isEmpty()) {
                return result.get(0).toString();
            } else {
                return "Không nhận được phản hồi từ AI.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Xin lỗi, AI đang gặp lỗi.";
        }
    }

}

