package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.MaritalStatusResponse;
import com.aztu.job_application.model.entity.userInformation.MaritalStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MaritalStatusService {

    MaritalStatus findById(long id);

    ResponseEntity<List<MaritalStatusResponse>> findAll();
}
