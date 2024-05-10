package com.aztu.job_application.model.dto.response;

import lombok.*;

import java.nio.file.Path;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplyResponse {

    String text;

    Path cv;

    UserResponse user;

    VacancyResponse vacancy;
}
