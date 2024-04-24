package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.AuthRequest;
import com.aztu.job_application.model.dto.response.AuthenticationResponse;
import com.aztu.job_application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authentication")
    public AuthenticationResponse authentication(@RequestBody @Valid AuthRequest user) {

        return authService.authentication(user);
    }
}
