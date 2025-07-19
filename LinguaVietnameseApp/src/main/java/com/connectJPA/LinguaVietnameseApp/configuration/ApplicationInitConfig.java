package com.connectJPA.LinguaVietnameseApp.configuration;

import com.connectJPA.LinguaVietnameseApp.entity.Role;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.repository.RoleRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository,
                                               RoleRepository roleRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ADMIN");
                roleRepository.save(role);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Set.of(String.valueOf(role)))
                        .build();

                userRepository.save(user);
                log.warn(">>> Admin user created with default password: admin <<<");
            } else {
                log.info(">>> Admin already exists <<<");
            }
        };
    }
}

