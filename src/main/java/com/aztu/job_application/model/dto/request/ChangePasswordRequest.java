package com.aztu.job_application.model.dto.request;

import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.service.impl.ExceptionService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.aztu.job_application.model.constant.ValidationConstant.PASSWORD_REGEX;
import static com.aztu.job_application.model.enums.ExceptionMessage.DIFFER_PASSWORD;

@Getter
@Setter
@RequiredArgsConstructor
public class ChangePasswordRequest {

    private ExceptionService exceptionService;

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = PASSWORD_REGEX
    )
    private String newPassword;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = PASSWORD_REGEX
    )
    private String repeatPassword;


    public ChangePasswordRequest(String newPassword, String repeatPassword) {
        differPassword(newPassword, repeatPassword);
        this.newPassword = newPassword;
        this.repeatPassword = repeatPassword;
    }

    private void differPassword(String newPassword, String repeatPassword) {

        if (!newPassword.equals(repeatPassword)) throw new ApplicationException(exceptionService.exceptionResponse(DIFFER_PASSWORD.getMessage(), DIFFER_PASSWORD.getHttpStatus()));
    }
}
