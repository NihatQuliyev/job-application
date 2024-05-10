package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.GenderResponse;
import com.aztu.job_application.model.dto.response.userInformation.MaritalStatusResponse;
import com.aztu.job_application.model.entity.userInformation.MaritalStatus;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.MaritalStatusRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.MaritalStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<MaritalStatusResponse>> findAll() {
        List<MaritalStatusResponse> maritalStatusResponses = maritalStatusRepository.findAll()
                .stream()
                .map(maritalStatus -> MaritalStatusResponse.builder()
                        .name(maritalStatus.getName())
                        .id(maritalStatus.getId())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(maritalStatusResponses);
    }
}
