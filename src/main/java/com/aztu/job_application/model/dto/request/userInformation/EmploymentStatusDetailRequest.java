package com.aztu.job_application.model.dto.request.userInformation;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
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

    @NotBlank(message = "Workplace name cannot be blank")
    private String workPlace;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @Past(message = "Start date invalid")
    private LocalDate startDate;

    @NotBlank(message = "University name cannot be blank")
    private String university;

    @NotBlank(message = "Qualification cannot be blank")
    private String qualification;

    @NotBlank(message = "Start and end date cannot be blank")
    private String startAndEndDate;


}
