package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.enums.LogAction;
import com.connectJPA.LinguaVietnameseApp.enums.TableLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
@SuperBuilder
public class Log extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String logId;

    private LogAction action;
    private TableLog targetTable;
    private String targetId;
    private String description;

    private String userId;

}

