package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
}