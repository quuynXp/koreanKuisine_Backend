package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByIsAvailableTrue();
}
