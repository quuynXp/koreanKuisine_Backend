package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.RoomDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Room;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.mapper.RoomMapper;
import com.connectJPA.LinguaVietnameseApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream().map(roomMapper::toDto).toList();
    }

    public Optional<RoomDTO> getById(String id) {
        return roomRepository.findById(id).map(roomMapper::toDto);
    }

    @Cacheable(value = "rooms", key = "#creatorId")
    public List<RoomDTO> getByCreator(String creatorId) {
        return roomRepository.findByCreatorId(creatorId).stream().map(roomMapper::toDto).toList();
    }

    @CacheEvict(value = "rooms", key = "#creatorId")
    public RoomDTO create(RoomDTO dto) {
        return roomMapper.toDto(roomRepository.save(roomMapper.toEntity(dto)));
    }

    public Optional<RoomDTO> update(String id, RoomDTO dto) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomName(dto.getRoomName());
            room.setPurpose(dto.getPurpose());
            room.setStatus(dto.getStatus());
            return roomMapper.toDto(roomRepository.save(room));
        });
    }

    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    public void addUserToRoom(String roomId, String userId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getMemberIds() == null) {
            room.setMemberIds(new HashSet<>());
        }

        if (room.getMemberIds().contains(userId)) {
            throw new RuntimeException("User already in room");
        }

        if (room.getMemberIds().size() >= room.getMaxMembers()) {
            throw new RuntimeException("Room is full");
        }

        room.getMemberIds().add(userId);
        roomRepository.save(room);
    }

    public void removeUserFromRoom(String roomId, String userId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getMemberIds() == null || !room.getMemberIds().contains(userId)) {
            throw new RuntimeException("User not in room");
        }

        room.getMemberIds().remove(userId);
        roomRepository.save(room);
    }



    public List<RoomDTO> getRoomsByUser(String userId) {
        List<Room> rooms = roomRepository.findAllByMemberIdsContaining(userId);
        return rooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RoomDTO convertToDTO(Room room) {
        return RoomDTO.builder()
                .roomId(room.getRoomId())
                .roomName(room.getRoomName())
                .creatorId(room.getCreatorId())
                .purpose(room.getPurpose())
                .status(room.getStatus())
                .maxMembers(room.getMaxMembers())
                .roomType(room.getRoomType())
                .createdAt(room.getCreatedAt())
                .deletedAt(room.getDeletedAt())
                .isDeleted(room.isDeleted())
                .memberIds(room.getMemberIds())
                .build();
    }

}
