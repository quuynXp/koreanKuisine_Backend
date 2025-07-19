package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.Character3DDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Character3D;
import com.connectJPA.LinguaVietnameseApp.mapper.Character3DMapper;
import com.connectJPA.LinguaVietnameseApp.repository.Character3DRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class Character3DService {
    private final Character3DRepository characterRepository;
    private final Character3DMapper characterMapper;

    public List<Character3DDTO> getAllCharacters() {
        return characterRepository.findAll().stream().map(characterMapper::toDto).toList();
    }

    public Character3DDTO create(Character3DDTO dto) {
        characterRepository.save(characterMapper.toEntity(dto));
        return dto;
    }

    public Character3DDTO update(String id, Character3DDTO dto) {
        return characterRepository.findById(id).map(character3d -> {
            character3d.setDescription(dto.getDescription());
            character3d.setModelUrl(dto.getModelUrl());
            Character3D character3dNew = characterMapper.toEntity(dto);
            characterRepository.save(character3d);
            return characterMapper.toDto(character3dNew);
        }).orElseThrow(() -> new RuntimeException("Character not found"));
    }

    public void delete(String id) {
        characterRepository.deleteById(id);
    }
}

