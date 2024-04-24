package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {
}