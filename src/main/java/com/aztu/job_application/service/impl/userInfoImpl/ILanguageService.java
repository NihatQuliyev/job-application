package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.entity.userInformation.Language;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.LanguageRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
