package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.LessonOrderInSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonOrderInSeriesRepository extends JpaRepository<LessonOrderInSeries, UUID> {
}
