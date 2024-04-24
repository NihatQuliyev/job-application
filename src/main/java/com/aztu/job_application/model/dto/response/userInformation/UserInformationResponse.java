package com.aztu.job_application.model.dto.response.userInformation;

import lombok.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserInformationResponse {
    private String fatherName;

    private GenderResponse gender;

    private LocalDate birthdate;

    private String phoneNumber;

    private String fin;

    private String address;

    private String liveAddress;

    private MilitaryQualificationResponse militaryQualification;

    private MaritalStatusResponse maritalStatus;


    //TODO: Education and Experience
    private EducationLevelDetailResponse educationLevelDetail;

    ////

    private List<EmploymentStatusDetailResponse> employmentStatusDetail;


    //TODO: Professional info
    private List<LanguageResponse> languages;

    private List<SoftSkillResponse> softSkills;

    private String others;

    private Path profileImage;

}
