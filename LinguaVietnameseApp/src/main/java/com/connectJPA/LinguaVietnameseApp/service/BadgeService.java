package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.BadgeDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Badge;
import com.connectJPA.LinguaVietnameseApp.mapper.BadgeMapper;
import com.connectJPA.LinguaVietnameseApp.repository.BadgeRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserBadgeRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;
    private final UserRepository userRepository;
    private final UserBadgeRepository userBadgeRepository;

    public List<BadgeDTO> getAllBadges() {
        return badgeRepository.findAll().stream().map(badgeMapper::toDto).toList();
    }
    public BadgeDTO create(BadgeDTO dto) {
        return badgeMapper.toDto(badgeRepository.save(badgeMapper.toEntity(dto)));
    }
    @Cacheable(value = "badges", key = "#userId")
    public List<BadgeDTO> getByUserId(String userId) {
        return userBadgeRepository.findByUserBadgeIdUserId(userId).stream().map(userBadge -> badgeMapper.toDto((Badge) badgeRepository.findAllById(Collections.singleton(userBadge.getUserBadgeId().getBadgeName())))).toList();
    }

    @CacheEvict(value = "badges", key =  "#userId")
    public void setDefaultBadge(String userId, String badgeId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setBadgeDefaultId(badgeId);
            userRepository.save(user);
        });
    }

    public BadgeDTO update(String badgeId, BadgeDTO dto) {
        return badgeRepository.findById(badgeId).map(badge -> {
            badge.setBadgeName(dto.getBadgeName());
            badge.setDescription(dto.getBadgeDescription());
            badge.setImageUrl(dto.getBadgeImageUrl());
            return badgeMapper.toDto(badgeRepository.save(badge));
        }).orElseThrow(() -> new RuntimeException("Badge not found"));
    }

    public void delete(String badgeId) {
        badgeRepository.deleteById(badgeId);
    }
}
