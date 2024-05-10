package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.SoftSkillResponse;
import com.aztu.job_application.model.entity.userInformation.SoftSkill;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SoftSkillService {

    List<SoftSkill> findAll(List<Long> softSkillsId);

    ResponseEntity<List<SoftSkillResponse>> findAll();
}
