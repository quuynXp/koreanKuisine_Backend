package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "character3d_id")
    private UUID character3dId;

    @Column(name = "badge_id")
    private UUID badgeId;

    @Column(name = "native_language_id")
    private UUID nativeLanguageId;

    @Column(name = "auth_provider")
    private Integer authProvider;

    @Column(name = "country")
    private Integer country;

    @Column(name = "level", nullable = false)
    private Integer level = 1;

    @Column(name = "score", nullable = false)
    private Integer score = 0;

    @Column(name = "streak", nullable = false)
    private Integer streak = 0;
}
