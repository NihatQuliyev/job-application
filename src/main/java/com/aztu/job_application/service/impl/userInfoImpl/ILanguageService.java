package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.LanguageAndLevelResponse;
import com.aztu.job_application.model.dto.response.userInformation.LanguageResponse;
import com.aztu.job_application.model.entity.userInformation.Language;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.LanguageRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ILanguageService implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ExceptionService exceptionService;

    @Override
    public Language findById(long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(LANGUAGE_NOT_FOUND.getMessage(), LANGUAGE_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<LanguageResponse>> findAll() {

        List<LanguageResponse> languageResponses = languageRepository.findAll()
                .stream()
                .map(language -> LanguageResponse.builder()
                        .language(language.getName())
                        .id(language.getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(languageResponses);
    }
}
