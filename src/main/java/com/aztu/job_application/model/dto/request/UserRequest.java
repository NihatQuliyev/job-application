package com.aztu.job_application.model.dto.request;

import com.aztu.job_application.model.dto.request.userInformation.UserInformationRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserRequest {

    private String name;

    private String surname;

    private String email;

    private String password;

    private UserInformationRequest userInformation;
}
