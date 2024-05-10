package com.aztu.job_application.model.dto.request.userInformation;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationLevelDetailRequest {
    private long educationLevelId;

    @NotBlank(message = "University name cannot be blank")
    private String university;

    @NotBlank(message = "Qualification cannot be blank")
    private String qualification;

    @NotBlank(message = "Start and end date cannot be blank")
    private String startAndEndDate;

    @NotBlank(message = "Degree cannot be blank")
    private String degree;

}
