package com.connectJPA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<com.connectJPA.demo.entity.InvalidatedToken, String> {
}
