package com.aztu.job_application.model.dto.response.userInformation;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EmploymentStatusDetailResponse {
    private EmploymentStatusResponse employmentStatus;

    private String workPlace;

    private String position;

    private LocalDate startDate;

    private String university;

    private String qualification;

    private String startAndEndDate;

}
