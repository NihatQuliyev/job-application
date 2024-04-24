package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.EducationLevel;

import java.util.List;

public interface EducationLevelService {

    EducationLevel findById(long id);
    List<EducationLevel> findAll();
}
