package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDetailRequest {

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @Positive(message = "Salary must be a positive number")
    private BigDecimal salary;

    private int age;
    @NotBlank(message = "Education cannot be blank")
    private String education;

    @NotBlank(message = "Job experience cannot be blank")
    private String jobExperience;

    @NotBlank(message = "Relevant person cannot be blank")
    private String relevantPerson;

    @NotBlank(message = "Candidate requirements cannot be blank")
    private String candidateRequirements;

    @NotBlank(message = "Job information cannot be blank")
    private String jobInformation;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Contact number cannot be blank")
    private String contactNumber;

}
