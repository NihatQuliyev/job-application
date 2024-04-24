package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.entity.userInformation.LanguageLevel;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.LanguageLevelRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
