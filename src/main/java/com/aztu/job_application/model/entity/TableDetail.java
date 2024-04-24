package com.aztu.job_application.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "_table_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TableDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updateAt = LocalDateTime.now();

    @Builder.Default
    private boolean status = true;
}
