package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.GenderResponse;
import com.aztu.job_application.model.entity.userInformation.Gender;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenderService {

    Gender findById(long id);

    ResponseEntity<List<GenderResponse>> findAll();
}
