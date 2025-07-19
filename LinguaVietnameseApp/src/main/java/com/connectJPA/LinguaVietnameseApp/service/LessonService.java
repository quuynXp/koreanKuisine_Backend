package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.LessonDTO;
import com.connectJPA.LinguaVietnameseApp.mapper.LessonMapper;
import com.connectJPA.LinguaVietnameseApp.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll().stream().map(lessonMapper::toDto).toList();
    }

    public Optional<LessonDTO> getById(String id) {
        return lessonRepository.findById(id).map(lessonMapper::toDto);
    }
    public LessonDTO create(LessonDTO dto) {
        return lessonMapper.toDto(lessonRepository.save(lessonMapper.toEntity(dto)));
    }

    public void delete(String id) {
        lessonRepository.deleteById(id);
    }
}
