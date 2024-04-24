package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.mapper.EmploymentStatusDetailMapper;
import com.aztu.job_application.mapper.UserInformationMapper;
import com.aztu.job_application.mapper.UserMapper;
import com.aztu.job_application.model.dto.request.userInformation.EmploymentStatusDetailRequest;
import com.aztu.job_application.model.dto.request.userInformation.LanguageRequest;
import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.userInformation.*;
import com.aztu.job_application.repository.UserRepository;
import com.aztu.job_application.service.userInformation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IUserInformationService implements UserInformationService {

    private final GenderService genderService;
    private final MilitaryQualificationService militaryQualificationService;
    private final MaritalStatusService maritalStatusService;
    private final EducationLevelService educationLevelService;
    private final EmploymentStatusService employmentStatusService;
    private final LanguageService languageService;
    private final LanguageLevelService languageLevelService;
    private final SoftSkillService softSkillService;
    private final UserInformationMapper userInformationMapper;
    private final EmploymentStatusDetailMapper employmentStatusDetailMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserInformation buildUserInformation(UserRequest userRequest) {

        UserInformation userInformation =  userInformationMapper.map(userRequest.getUserInformation());

        Gender gender = genderService.findById(userRequest.getUserInformation().getGenderId());
        MilitaryQualification militaryQualification = militaryQualificationService.findById(userRequest.getUserInformation().getMilitaryQualificationId());
        MaritalStatus maritalStatus = maritalStatusService.findById(userRequest.getUserInformation().getMaritalStatusId());
        EducationLevel educationLevel = educationLevelService.findById(userRequest.getUserInformation().getEducationLevelDetail().getEducationLevelId());

        List<EmploymentStatusDetail> employmentStatusDetails = new LinkedList<>();
        for (EmploymentStatusDetailRequest employmentStatusDetailRequest: userRequest.getUserInformation().getEmploymentStatusDetail()) {

            EmploymentStatus employmentStatus = employmentStatusService.findById(employmentStatusDetailRequest.getEmploymentStatusId());

            EmploymentStatusDetail employmentStatusDetail = employmentStatusDetailMapper.map(employmentStatusDetailRequest);
            employmentStatusDetail.setEmploymentStatus(employmentStatus);
            employmentStatusDetails.add(employmentStatusDetail);
        }

        List<Language> languagesList = new LinkedList<>();
        for (LanguageRequest languageRequest : userRequest.getUserInformation().getLanguages()) {

            Language language = languageService.findById(languageRequest.getLanguageId());
            language.setLanguageLevel(languageLevelService.findById(languageRequest.getLanguageLevelId()));
            languagesList.add(language);
        }

        List<SoftSkill> softSkills = softSkillService.findAll(userRequest.getUserInformation().getSoftSkillsId());;

        userInformation.setGender(gender);
        userInformation.setMilitaryQualification(militaryQualification);
        userInformation.setMaritalStatus(maritalStatus);
        userInformation.getEducationLevelDetail().setEducationLevel(educationLevel);
        userInformation.setSoftSkills(softSkills);
        userInformation.setEmploymentStatusDetail(employmentStatusDetails);
        userInformation.setLanguages(languagesList);

        return userInformation;
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByNameOrSurname(String key) {
        List<UserResponse> users = userRepository.findByNameOrSurname(key).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByEducationLevel(long educationLevelId) {
        List<UserResponse> users = userRepository.findByUserInformation_EducationLevelDetail_EducationLevel_Id(educationLevelId).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByUserInformation_Address(String address) {
        List<UserResponse> users = userRepository.findByUserInformation_Address(address).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByUserInformation_MilitaryQualification_Id(long militaryQualificationId) {
        List<UserResponse> users = userRepository
                .findByUserInformation_MilitaryQualification_Id(militaryQualificationId).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
