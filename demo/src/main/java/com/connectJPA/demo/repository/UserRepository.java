package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , String> {

    boolean existsByUsername(String username);
    boolean existsByMail(String mail);
    boolean existsByPhone(String phone);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.mail = ?2 OR u.phone = ?3")
    Optional<User> findByUsernameOrMailOrPhone(String username, String mail, String phone);

}
