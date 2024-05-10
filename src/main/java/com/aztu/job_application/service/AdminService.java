package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.AdminRequest;
import com.aztu.job_application.model.dto.request.PasswordRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {
    ResponseEntity<Void> registration(AdminRequest adminRequest, MultipartFile multipartFile);

    ResponseEntity<Void> adminConfirmation(String token, PasswordRequest passwordRequest);

}
