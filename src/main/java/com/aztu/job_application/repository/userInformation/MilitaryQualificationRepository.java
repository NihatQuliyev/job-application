package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.MilitaryQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitaryQualificationRepository extends JpaRepository<MilitaryQualification, Long> {
}