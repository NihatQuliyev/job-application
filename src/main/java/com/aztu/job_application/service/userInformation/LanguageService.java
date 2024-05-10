package com.aztu.job_application.service.userInformation;

import com.aztu.job_application.model.dto.response.userInformation.LanguageAndLevelResponse;
import com.aztu.job_application.model.dto.response.userInformation.LanguageResponse;
import com.aztu.job_application.model.entity.userInformation.Language;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageService {

    Language findById(long id);

    ResponseEntity<List<LanguageResponse>> findAll();
}
