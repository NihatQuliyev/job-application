package com.aztu.job_application.model.dto.request.userInformation;

import com.aztu.job_application.validator.GraterThan;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Father's name cannot be blank")
    private String fatherName;

    private int genderId;

    @GraterThan(message = "You must be over 18 years old")
    private LocalDate birthdate;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "FIN (Personal Identification Number) cannot be blank")
    @Pattern(
            regexp = "^[0-9a-zA-Z]{7}$",
            message = "INVALID_FIN"
    )
    private String fin;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Live address cannot be blank")
    private String liveAddress;

    private int militaryQualificationId;

    private int maritalStatusId;

    //TODO: Education and Experience
    @Valid
    private EducationLevelDetailRequest educationLevelDetail;

    ////
    @Valid
    private List<EmploymentStatusDetailRequest> employmentStatusDetail;

    //TODO: Professional info
    private List<LanguageRequest> languages;

    private List<Long> softSkillsId;

    private String others;

}
