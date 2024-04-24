package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.userInformation.UserInformation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInformationService {

    UserInformation buildUserInformation(UserRequest userRequest);

    ResponseEntity<List<UserResponse>> findByNameOrSurname(String key);
    ResponseEntity<List<UserResponse>> findByEducationLevel(long educationLevelId);
    ResponseEntity<List<UserResponse>> findByUserInformation_Address(String address);
    ResponseEntity<List<UserResponse>> findByUserInformation_MilitaryQualification_Id(long militaryQualificationId);
}
