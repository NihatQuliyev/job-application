package com.aztu.job_application.model.dto.response.userInformation;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EducationLevelDetailResponse {
    private EducationLevelResponse educationLevel;

    private String university;

    private String qualification;

    private String startAndEndDate;

    private String degree;

}
