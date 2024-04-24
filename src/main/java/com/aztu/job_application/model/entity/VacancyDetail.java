package com.aztu.job_application.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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