package com.aztu.job_application.service.impl;

import com.aztu.job_application.helper.EmailServiceHelper;
import com.aztu.job_application.helper.TokenServiceHelper;
import com.aztu.job_application.model.dto.request.EmailRequest;
import com.aztu.job_application.model.dto.response.ExceptionResponse;
import com.aztu.job_application.model.entity.Token;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.TokenRepository;
import com.aztu.job_application.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ITokenService implements TokenService {

    private final TokenServiceHelper tokenServiceHelper;
    private final TokenRepository tokenRepository;
    private final EmailServiceHelper emailServiceHelper;
    private final EmailService emailService;

    public Token buildToken(User user) {
        Token token = tokenServiceHelper.getTokenBuild(user);
        tokenRepository.save(token);
        return token;
    }
    @Override
    public void userConfirm(User user) {
        Token token = buildToken(user);
        EmailRequest emailRequest = emailServiceHelper.sendEmailToVerifyUser(token, user);
        emailService.sendEmail(emailRequest);
    }


    @Override
    public void adminConfirm(User user) {
        Token token = buildToken(user);
        EmailRequest emailRequest = emailServiceHelper.sendEmailToVerifyAdmin(token, user);
        emailService.sendEmail(emailRequest);
    }

    @Override
    public Map<String, User> getByToken(String token) {
        Token userToken = findByToken(token);

        return Map.of(userToken.getToken(),userToken.getUser());
    }

    @Override
    public void delete(String token) {
        tokenRepository.delete(findByToken(token));
    }

    private Token findByToken(String token) {

        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ApplicationException(ExceptionResponse.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(ExceptionMessage.TOKEN_NOT_FOUND.getMessage())
                        .build()));
    }
}
