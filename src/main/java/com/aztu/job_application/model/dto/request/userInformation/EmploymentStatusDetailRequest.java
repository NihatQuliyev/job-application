package com.aztu.job_application.model.dto.request.userInformation;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Getter
@Setter
@ToString
public class EmploymentStatusDetailRequest {

    private long employmentStatusId;

    private String workPlace;

    private String position;

    private LocalDate startDate;

    private String university;

    private String qualification;

    private String startAndEndDate;

}
