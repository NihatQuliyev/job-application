package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.EducationLevelResponse;
import com.aztu.job_application.model.dto.response.userInformation.MaritalStatusResponse;
import com.aztu.job_application.model.entity.userInformation.EducationLevel;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.EducationLevelRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.EDUCATION_LEVEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IEducationLevelService implements EducationLevelService {

    private final EducationLevelRepository educationLevelRepository;
    private final ExceptionService exceptionService;

    @Override
    public EducationLevel findById(long id) {
        return educationLevelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(EDUCATION_LEVEL_NOT_FOUND.getMessage(),EDUCATION_LEVEL_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<EducationLevelResponse>> findAll() {
        List<EducationLevelResponse> educationLevelResponses = educationLevelRepository.findAll()
                .stream()
                .map(maritalStatus -> EducationLevelResponse.builder()
                        .name(maritalStatus.getName())
                        .id(maritalStatus.getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(educationLevelResponses);
    }
}
