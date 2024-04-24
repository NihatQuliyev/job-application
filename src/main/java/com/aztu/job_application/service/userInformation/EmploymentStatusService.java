package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.EmploymentStatus;

import java.util.List;

public interface EmploymentStatusService {

    EmploymentStatus findById(long id);

    List<EmploymentStatus> findAll();

}
