package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.ChangePasswordRequest;
import com.aztu.job_application.model.dto.request.PasswordRequest;
import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.RoleType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    ResponseEntity<Void> userRegistration(UserRequest request, MultipartFile multipartFile);

    ResponseEntity<Void> userConfirmation(String token);

    User getConfirmUser(String token);

    ResponseEntity<String> userRenewPassword(String username);

    ResponseEntity<String> resetPassword(String username, int otp, PasswordRequest updatePassword);

    ResponseEntity<User> findByUsername(String username);

    ResponseEntity<String> userChangePassword(ChangePasswordRequest changePasswordRequest);

    User registration(User user, RoleType roleType, MultipartFile multipartFile);

    ResponseEntity<List<UserResponse>> findAllUsers();

}
