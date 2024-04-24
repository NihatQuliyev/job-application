package com.aztu.job_application.repository.userInformation;

import com.aztu.job_application.model.entity.userInformation.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}