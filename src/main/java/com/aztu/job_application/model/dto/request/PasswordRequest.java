package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.aztu.job_application.model.constant.ValidationConstant.PASSWORD_REGEX;

@NoArgsConstructor
@Getter
@Setter
public class PasswordRequest {

    @NotBlank(message = "Update password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = PASSWORD_REGEX
    )
    private String updatePassword;

    @NotBlank(message = "Repeat password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = PASSWORD_REGEX
    )
    private String repeatPassword;


    public PasswordRequest(String updatePassword, String repeatPassword) {
        differPassword(updatePassword,repeatPassword);
        this.updatePassword = updatePassword;
        this.repeatPassword = repeatPassword;
    }

    private void differPassword(String updatePassword, String repeatPassword) {

        if (!updatePassword.equals(repeatPassword)) throw new RuntimeException("Differ password");
    }
}
