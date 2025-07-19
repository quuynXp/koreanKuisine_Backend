package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.LogDTO;
import com.connectJPA.LinguaVietnameseApp.mapper.LogMapper;
import com.connectJPA.LinguaVietnameseApp.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository repo;
    private final LogMapper mapper;

    public List<LogDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public LogDTO getOne(String id) {
        return mapper.toDto(repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy log")));
    }
}

