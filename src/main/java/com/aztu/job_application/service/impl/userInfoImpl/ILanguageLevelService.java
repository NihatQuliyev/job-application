package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.LanguageLevelResponse;
import com.aztu.job_application.model.entity.userInformation.LanguageLevel;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.LanguageLevelRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.LANGUAGE_LEVEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ILanguageLevelService implements LanguageLevelService {

    private final LanguageLevelRepository languageLevelRepository;
    private final ExceptionService exceptionService;

    @Override
    public LanguageLevel findById(long id) {
        return languageLevelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(LANGUAGE_LEVEL_NOT_FOUND.getMessage(), LANGUAGE_LEVEL_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<LanguageLevelResponse>> findAll() {

        return ResponseEntity.ok(languageLevelRepository.findAll()
                .stream()
                .map(languageLevel -> LanguageLevelResponse.builder()
                        .languageLevel(languageLevel.getName())
                        .id(languageLevel.getId())
                        .build())
                .collect(Collectors.toList()));
    }
}
