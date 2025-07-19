package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.UserRole;
import com.connectJPA.LinguaVietnameseApp.entity.id.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findAllIdUserId(UUID userId);

    Optional<UserRole> findByIdRoleId(UUID roleId);
    List<UserRole> findAllByIdUserIdAndIdRoleId(UUID userId, UUID roleId);
}

