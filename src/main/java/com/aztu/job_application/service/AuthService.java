package com.aztu.job_application.service;


import com.aztu.job_application.model.dto.request.AuthRequest;
import com.aztu.job_application.model.dto.response.AuthenticationResponse;
import com.aztu.job_application.model.entity.User;

public interface AuthService {


    AuthenticationResponse authentication(AuthRequest dto);
    AuthenticationResponse accessTokensByRefreshToken(String refreshToken);
    User getAuthenticatedUser();
}
