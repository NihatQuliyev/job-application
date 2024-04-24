package com.aztu.job_application.model.entity.userInformation;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationLevelDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;

    private String qualification;

    private String startAndEndDate;

    private String degree;

    @OneToOne
    private EducationLevel educationLevel;

}