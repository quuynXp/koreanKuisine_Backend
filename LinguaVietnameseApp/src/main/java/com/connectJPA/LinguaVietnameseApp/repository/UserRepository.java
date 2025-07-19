package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User , String> {
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<User> findByUsername(String username);
    Optional<User> findByPhone(String phone);



    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?2 OR u.phone = ?3")
    Optional<User> findByUsernameOrMailOrPhone(String username, String mail, String phone);

}
