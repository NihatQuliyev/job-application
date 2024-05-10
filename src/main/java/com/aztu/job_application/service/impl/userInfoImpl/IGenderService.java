package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.GenderResponse;
import com.aztu.job_application.model.entity.userInformation.Gender;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.GenderRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.aztu.job_application.model.enums.ExceptionMessage.GENDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IGenderService implements GenderService {

    private final GenderRepository genderRepository;
    private final ExceptionService exceptionService;

    @Override
    public Gender findById(long id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(GENDER_NOT_FOUND.getMessage(), GENDER_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<GenderResponse>> findAll() {
        List<GenderResponse> genderResponses = genderRepository.findAll().stream()
                .map(gender -> GenderResponse.builder()
                        .name(gender.getName())
                        .id(gender.getId())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(genderResponses);
    }



}
