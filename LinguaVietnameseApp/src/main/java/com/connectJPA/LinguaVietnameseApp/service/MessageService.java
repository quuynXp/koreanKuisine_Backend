package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.entity.ChatMessage;
import com.connectJPA.LinguaVietnameseApp.repository.MessageRepository;
import com.connectJPA.LinguaVietnameseApp.repository.RoomRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessage message = new ChatMessage();
        message.setRoomId(String.valueOf(roomRepository.getReferenceById(chatMessage.getRoomId())));
        message.setSenderId(String.valueOf(userRepository.getReferenceById(chatMessage.getSenderId())));
        message.setContent(chatMessage.getContent());
        message.setMessageType(chatMessage.getMessageType());
        message.setCreatedAt(LocalDateTime.now());
        message.setRead(false);
        messageRepository.save(message);
        return  message;
    }
}