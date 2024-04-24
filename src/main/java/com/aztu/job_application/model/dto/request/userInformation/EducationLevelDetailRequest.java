package com.aztu.job_application.model.dto.request.userInformation;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationLevelDetailRequest {
    private long educationLevelId;

    private String university;

    private String qualification;

    private String startAndEndDate;

    private String degree;

}
