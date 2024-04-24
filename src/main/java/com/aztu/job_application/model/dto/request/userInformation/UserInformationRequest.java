package com.aztu.job_application.model.dto.request.userInformation;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserInformationRequest {

    //TODO: Personal info
    private String fatherName;

    private int genderId;

    private LocalDate birthdate;

    private String phoneNumber;

    private String fin;

    private String address;

    private String liveAddress;

    private int militaryQualificationId;

    private int maritalStatusId;


    //TODO: Education and Experience
    private EducationLevelDetailRequest educationLevelDetail;

    ////

    private List<EmploymentStatusDetailRequest> employmentStatusDetail;


    //TODO: Professional info
    private List<LanguageRequest> languages;

    private List<Long> softSkillsId;

    private String others;

}
