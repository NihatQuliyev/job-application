package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.MilitaryQualificationResponse;
import com.aztu.job_application.model.entity.userInformation.MilitaryQualification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MilitaryQualificationService {

    MilitaryQualification findById(long id);

    ResponseEntity<List<MilitaryQualificationResponse>> findAll();
}
