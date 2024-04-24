package com.aztu.job_application.model.dto.response;

import com.aztu.job_application.model.dto.response.userInformation.UserInformationResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserResponse {
    private String name;

    private String surname;

    private String email;

    private String password;

    private UserInformationResponse userInformation;
}
