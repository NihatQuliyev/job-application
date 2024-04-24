package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.service.userInformation.UserInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user-informations")
public class UserInformationController {
    private final UserInformationService userInformationService;

    @GetMapping("/search-by-name-or-surname")
    public ResponseEntity<List<UserResponse>> findByNameOrSurname(@RequestParam String key) {
        return userInformationService.findByNameOrSurname(key);
    }

    @GetMapping("/search-by-education-level")
    public ResponseEntity<List<UserResponse>> findByEducationLevel(@RequestParam long educationLevelId) {
        return userInformationService.findByEducationLevel(educationLevelId);
    }

    @GetMapping("/search-by-address")
    public ResponseEntity<List<UserResponse>> findByUserInformation_Address(@RequestParam String address) {
        return userInformationService.findByUserInformation_Address(address);
    }

    @GetMapping("/search-by-military-qualification")
    public ResponseEntity<List<UserResponse>> findByUserInformation_MilitaryQualification_Id(
            @RequestParam long militaryQualificationId) {

        return userInformationService.findByUserInformation_MilitaryQualification_Id(militaryQualificationId);
    }
}
