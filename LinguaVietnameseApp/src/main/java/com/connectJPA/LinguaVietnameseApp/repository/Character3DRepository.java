package com.connectJPA.LinguaVietnameseApp.repository;


import com.connectJPA.LinguaVietnameseApp.entity.Character3D;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Character3DRepository extends JpaRepository<Character3D, String> {
}

