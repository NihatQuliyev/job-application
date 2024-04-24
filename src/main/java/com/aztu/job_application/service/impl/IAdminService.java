package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.UserMapper;
import com.aztu.job_application.model.dto.request.AdminRequest;
import com.aztu.job_application.model.dto.request.PasswordRequest;
import com.aztu.job_application.model.dto.response.CompanyResponse;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.Company;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.RoleType;
import com.aztu.job_application.service.AdminService;
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.TokenService;
import com.aztu.job_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IAdminService implements AdminService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;


    @Override
    @Transactional
    public ResponseEntity<Void> registration(AdminRequest adminRequest) {
        User user = userMapper.map(adminRequest);

        String password = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(password));
        User admin = userService.registration(user, RoleType.ADMIN);
        tokenService.adminConfirm(admin);

        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> adminConfirmation(String token, PasswordRequest passwordRequest) {
        User user = userService.getConfirmUser(token);
        user.setPassword(passwordEncoder.encode(passwordRequest.getUpdatePassword()));
        user.setEnable(true);
        tokenService.delete(token);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<User> users = userService.findAllUsers();

        List<UserResponse> userResponses = new LinkedList<>();

        for (User user : users) {
            UserResponse userResponse = userMapper.map(user);
            userResponse.getUserInformation().setProfileImage(fileService.findByName(user.getUserInformation().getProfileImage()));
            userResponses.add(userResponse);
        }
        return ResponseEntity.ok(userResponses);
    }

}
