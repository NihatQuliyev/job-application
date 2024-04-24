package com.aztu.job_application.model.entity.userInformation;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentStatusDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workPlace;

    private String position;

    private LocalDate startDate;

    private String university;

    private String qualification;

    private String startAndEndDate;

    @OneToOne
    private EmploymentStatus employmentStatus;
}
