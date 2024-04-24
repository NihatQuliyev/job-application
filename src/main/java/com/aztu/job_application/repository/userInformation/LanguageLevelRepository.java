package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.LanguageLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageLevelRepository extends JpaRepository<LanguageLevel, Long> {
}