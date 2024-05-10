package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.LanguageLevelResponse;
import com.aztu.job_application.model.entity.userInformation.LanguageLevel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageLevelService {

    LanguageLevel findById(long id);

    ResponseEntity<List<LanguageLevelResponse>> findAll();
}
