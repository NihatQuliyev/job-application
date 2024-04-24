package com.aztu.job_application.repository;


import com.aztu.job_application.model.entity.RefreshToken;
import com.aztu.job_application.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByTokenAndRevoked(String refreshToken, boolean revoked);

}
