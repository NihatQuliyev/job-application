package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}