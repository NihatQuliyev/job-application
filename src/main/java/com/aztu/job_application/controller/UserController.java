package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.ChangePasswordRequest;
import com.aztu.job_application.model.dto.request.PasswordRequest;
import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestPart("data") @Valid UserRequest userRequest,
                                             @RequestPart("image") MultipartFile multipartFile) {
        return userService.userRegistration(userRequest, multipartFile);
    }


    @GetMapping("/confirmation")
    public ResponseEntity<Void> confirmation(@RequestParam String token) {

        return userService.userConfirmation(token);
    }

    @PostMapping("/renew-password/{username}")
    public ResponseEntity<String> userRenewPassword(@PathVariable String username) {
        System.out.println(username);

        return userService.userRenewPassword(username);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> userResetPassword(@RequestParam String username,
                                                    @RequestParam int otp,
                                                    @RequestBody @Valid PasswordRequest updatePassword) {

        return userService.resetPassword(username, otp, updatePassword);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<String> userChangePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return userService.userChangePassword(changePasswordRequest);
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return userService.findAllUsers();
    }
}
