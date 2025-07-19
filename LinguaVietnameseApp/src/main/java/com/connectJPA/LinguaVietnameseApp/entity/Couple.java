package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.CouplesId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "couples")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Couple extends BaseEntity {
    @EmbeddedId
    private CouplesId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CoupleStatus status = CoupleStatus.PENDING;

    public enum CoupleStatus {
        PENDING, ACTIVE, REJECTED
    }

}
