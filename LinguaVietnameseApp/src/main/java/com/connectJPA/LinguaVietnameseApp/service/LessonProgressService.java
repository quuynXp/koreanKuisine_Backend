package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.LessonProgressDTO;
import com.connectJPA.LinguaVietnameseApp.mapper.LessonProgressMapper;
import com.connectJPA.LinguaVietnameseApp.repository.LessonProgressRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LessonProgressService {
    private LessonProgressMapper lessonProgressMapper;
    private LessonProgressRepository lessonProgressRepository;

    @Cacheable(value = "lessonProgress", key = "#userId")
    public List<LessonProgressDTO> getByUser(String userId) {
        return lessonProgressRepository.findByLessonProgressIdUserId(userId).stream().map(lessonProgressMapper::toDto).toList();
    }

    @CacheEvict(value = "lessonProgress", key = "#dto.userId")
    public LessonProgressDTO create(LessonProgressDTO dto) {
        return lessonProgressMapper.toDto(lessonProgressRepository.save(lessonProgressMapper.toEntity(dto)));
    }
}
