package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.EmploymentStatusResponse;
import com.aztu.job_application.model.entity.userInformation.EmploymentStatus;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.EmploymentStatusRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.EmploymentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.EMPLOYMENT_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IEmploymentStatusService implements EmploymentStatusService {

    private final EmploymentStatusRepository employmentStatusRepository;
    private final ExceptionService exceptionService;

    @Override
    public EmploymentStatus findById(long id) {
        return employmentStatusRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(EMPLOYMENT_STATUS_NOT_FOUND.getMessage(),EMPLOYMENT_STATUS_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<EmploymentStatusResponse>> findAll() {
        List<EmploymentStatusResponse> employmentStatusResponses = employmentStatusRepository.findAll()
                .stream()
                .map(maritalStatus -> EmploymentStatusResponse.builder()
                        .name(maritalStatus.getName())
                        .id(maritalStatus.getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(employmentStatusResponses);
    }
}
