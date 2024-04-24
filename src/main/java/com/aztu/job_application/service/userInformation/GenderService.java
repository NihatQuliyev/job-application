package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.Gender;

import java.util.List;

public interface GenderService {

    Gender findById(long id);

    List<Gender> findAll();
}
