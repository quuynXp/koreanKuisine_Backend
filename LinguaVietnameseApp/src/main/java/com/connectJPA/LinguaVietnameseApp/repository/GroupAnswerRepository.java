package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.GroupAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupAnswerRepository extends JpaRepository<GroupAnswer, String> {
    List<GroupAnswer> findByUserId(String userId);
}
