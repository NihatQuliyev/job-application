package com.aztu.job_application.model.dto.request;

import com.aztu.job_application.model.dto.request.userInformation.UserInformationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Validated
public class UserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 8 characters long")
    private String password;

    @Valid
    private UserInformationRequest userInformation;

}
