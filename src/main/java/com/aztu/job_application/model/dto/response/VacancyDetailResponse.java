package com.aztu.job_application.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDetailResponse {

    private String position;
    private String city;
    private BigDecimal salary;
    private int age;
    private String education;
    private String jobExperience;
    private String relevantPerson;
    private String candidateRequirements;
    private String jobInformation;
    private String email;
    private String contactNumber;
}
