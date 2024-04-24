package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.entity.userInformation.SoftSkill;

import java.util.List;

public interface SoftSkillService {

    List<SoftSkill> findAll(List<Long> softSkillsId);
}
