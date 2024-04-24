package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.entity.userInformation.Gender;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.userInformation.GenderRepository;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }
}
