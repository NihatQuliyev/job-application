package com.aztu.job_application.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminRequest {

    private String name;

    private String surname;

    private String email;

}
