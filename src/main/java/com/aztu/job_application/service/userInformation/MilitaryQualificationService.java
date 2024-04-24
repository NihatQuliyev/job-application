package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.MilitaryQualification;

import java.util.List;

public interface MilitaryQualificationService {

    MilitaryQualification findById(long id);

    List<MilitaryQualification> findAll();
}
