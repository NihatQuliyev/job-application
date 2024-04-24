package com.aztu.job_application.model.entity;

import com.aztu.job_application.model.enums.RoleType;
import com.aztu.job_application.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean confirm;

    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

    private String token;

    @Enumerated
    @Builder.Default
    private TokenType tokenType = TokenType.REGISTRATION;

    @OneToOne
    private User user;
}
