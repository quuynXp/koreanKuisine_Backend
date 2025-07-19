package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.CoupleDTO;
import com.connectJPA.LinguaVietnameseApp.mapper.CoupleMapper;
import com.connectJPA.LinguaVietnameseApp.repository.CoupleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CoupleService {
    private final CoupleRepository coupleRepository;
    private final CoupleMapper coupleMapper;

    public List<CoupleDTO> getAllCouples() {
        return coupleRepository.findAll().stream().map(coupleMapper::toDto).toList();
    }
    public CoupleDTO create(CoupleDTO dto) {
        return coupleMapper.toDto(coupleRepository.save(coupleMapper.toEntity(dto)));
    }

    public void delete(String user1Id, String user2Id) {
        coupleRepository.deleteByCoupleIdUser1IdAndCoupleIdUser2Id(user1Id, user2Id);
    }
    public Optional<CoupleDTO> getPartner(String userId) {
        return coupleRepository.findByCoupleIdUser1IdOrCoupleIdUser2Id(userId, userId).map(coupleMapper::toDto);
    }
}
