package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.EducationLevelResponse;
import com.aztu.job_application.model.entity.userInformation.EducationLevel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EducationLevelService {

    EducationLevel findById(long id);
    ResponseEntity<List<EducationLevelResponse>> findAll();
}
