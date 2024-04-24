package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.MaritalStatus;

import java.util.List;

public interface MaritalStatusService {

    MaritalStatus findById(long id);

    List<MaritalStatus> findAll();
}
