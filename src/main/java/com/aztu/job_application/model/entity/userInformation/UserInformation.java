package com.aztu.job_application.model.entity.userInformation;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fatherName;

    @ManyToOne
    private Gender gender;

    private LocalDate birthdate;

    private String phoneNumber;

    private String fin;

    private String address;

    private String liveAddress;

    @ManyToOne
    private MilitaryQualification militaryQualification;

    @ManyToOne
    private MaritalStatus maritalStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private EducationLevelDetail educationLevelDetail;

    @OneToMany(cascade = CascadeType.PERSIST)

    private List<EmploymentStatusDetail> employmentStatusDetail;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<LanguageAndLevel> languages;

    @ManyToMany
    private List<SoftSkill> softSkills;

    private String others;

    private String profileImage;

}
