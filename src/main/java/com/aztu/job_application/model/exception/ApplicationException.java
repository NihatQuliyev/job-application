package com.aztu.job_application.model.exception;

import com.aztu.job_application.model.dto.response.ExceptionResponse;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ExceptionResponse exceptionResponse;

    public ApplicationException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse.getMessage());
        this.exceptionResponse = exceptionResponse;
    }

}
