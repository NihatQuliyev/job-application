package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {
}