package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.EmploymentStatusResponse;
import com.aztu.job_application.model.entity.userInformation.EmploymentStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmploymentStatusService {

    EmploymentStatus findById(long id);

     ResponseEntity<List<EmploymentStatusResponse>> findAll();

}
