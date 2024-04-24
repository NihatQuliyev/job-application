package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.AdminRequest;
import com.aztu.job_application.model.dto.request.PasswordRequest;
import com.aztu.job_application.model.dto.request.VacancyRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody AdminRequest adminRequest) {
        return adminService.registration(adminRequest);
    }

    @PostMapping("/confirmation")
    public ResponseEntity<Void> confirmation(@RequestParam String token,
                                             @RequestBody PasswordRequest passwordRequest) {
        return adminService.adminConfirmation(token, passwordRequest);
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return adminService.findAllUsers();
    }

}
