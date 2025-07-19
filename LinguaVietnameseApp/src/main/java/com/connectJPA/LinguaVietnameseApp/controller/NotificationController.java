package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.NotificationDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.entity.Notification;
import com.connectJPA.LinguaVietnameseApp.mapper.NotificationMapper;
import com.connectJPA.LinguaVietnameseApp.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification", description = "Thông báo")
public class NotificationController {

    private final NotificationService service;
    private final NotificationMapper mapper;

    @GetMapping
    public ApiResponse<List<NotificationDTO>> getAll() {
        List<Notification> notifications = service.getAll();
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications){
            notificationDTOS.add(mapper.toDto(notification));
        }
        return ApiResponse.<List<NotificationDTO>>builder()
                .message("Notifications found")
                .result(notificationDTOS)
                .code(200)
                .build();
    }

    @PutMapping("/{id}/read")
    public ApiResponse<Void> markAsRead(@PathVariable String id) {
        service.markAsRead(id);
        return ApiResponse.<Void>builder()
                .message("Marked as read")
                .build();
    }
}

