package com.aztu.job_application.helper;

import com.aztu.job_application.model.entity.Token;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceHelper {

    public Token getTokenBuild(User user) {

        String uuid = String.valueOf(UUID.randomUUID());

        return Token.builder()
                .token(uuid)
                .tokenType(TokenType.CONFIRMATION)
                .user(user)
                .confirm(true)
                .build();
    }
}
