package com.aztu.job_application.service.impl;

import com.aztu.job_application.model.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public ExceptionResponse exceptionResponse(String message,
                                               HttpStatus httpStatus) {

        return ExceptionResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
}
