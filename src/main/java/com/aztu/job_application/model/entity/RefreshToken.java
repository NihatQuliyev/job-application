package com.aztu.job_application.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long lifeTime;
    private String token;
    private boolean revoked;

    @OneToOne
    private User user;

    @OneToOne
    private TableDetail tableDetail;
}
