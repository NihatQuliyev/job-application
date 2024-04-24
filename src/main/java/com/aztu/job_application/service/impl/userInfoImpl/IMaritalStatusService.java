package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.entity.userInformation.MaritalStatus;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.MaritalStatusRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.MaritalStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aztu.job_application.model.enums.ExceptionMessage.MARITAL_STATUS_EXCEPTION;

@Service
@RequiredArgsConstructor
public class IMaritalStatusService implements MaritalStatusService {

    private final MaritalStatusRepository maritalStatusRepository;
    private final ExceptionService exceptionService;

    @Override
    public MaritalStatus findById(long id) {

        return maritalStatusRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(MARITAL_STATUS_EXCEPTION.getMessage(), MARITAL_STATUS_EXCEPTION.getHttpStatus())));
    }

    @Override
    public List<MaritalStatus> findAll() {
        return maritalStatusRepository.findAll();
    }
}
