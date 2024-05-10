package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.MilitaryQualificationResponse;
import com.aztu.job_application.model.entity.userInformation.MilitaryQualification;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.MilitaryQualificationRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.MilitaryQualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.MILITARY_QUALIFICATION_EXCEPTION;


@Service
@RequiredArgsConstructor
public class IMilitaryQualificationService implements MilitaryQualificationService {

    private final MilitaryQualificationRepository militaryQualificationRepository;
    private final ExceptionService exceptionService;


    @Override
    public MilitaryQualification findById(long id) {
        return militaryQualificationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(MILITARY_QUALIFICATION_EXCEPTION.getMessage(),MILITARY_QUALIFICATION_EXCEPTION.getHttpStatus())));

    }

    @Override
    public ResponseEntity<List<MilitaryQualificationResponse>> findAll() {
        return ResponseEntity.ok(militaryQualificationRepository.findAll()
                .stream()
                .map(militaryQualification -> MilitaryQualificationResponse.builder()
                        .name(militaryQualification.getName())
                        .id(militaryQualification.getId())
                        .build())
                .collect(Collectors.toList()));
    }
}
