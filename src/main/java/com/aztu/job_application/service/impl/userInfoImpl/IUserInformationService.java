package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.mapper.EmploymentStatusDetailMapper;
import com.aztu.job_application.mapper.UserInformationMapper;
import com.aztu.job_application.mapper.UserMapper;
import com.aztu.job_application.model.dto.request.userInformation.EmploymentStatusDetailRequest;
import com.aztu.job_application.model.dto.request.userInformation.LanguageRequest;
import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.dto.response.userInformation.LanguageAndLevelResponse;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.entity.userInformation.*;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.UserRepository;
import com.aztu.job_application.repository.userInformation.LanguageLevelRepository;
import com.aztu.job_application.repository.userInformation.LanguageRepository;
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.impl.ExceptionService;
import com.aztu.job_application.service.userInformation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.aztu.job_application.model.enums.ExceptionMessage.LANGUAGE_LEVEL_NOT_FOUND;
import static com.aztu.job_application.model.enums.ExceptionMessage.LANGUAGE_NOT_FOUND;

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
    private final LanguageRepository languageRepository;
    private final LanguageLevelRepository languageLevelRepository;
    private final ExceptionService exceptionService;
    private final FileService fileService;


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

        List<LanguageAndLevel> languagesList = new LinkedList<>();
        for (LanguageRequest languageRequest : userRequest.getUserInformation().getLanguages()) {

            Language language = languageService.findById(languageRequest.getLanguageId());
            LanguageLevel languageLevel = languageLevelService.findById(languageRequest.getLanguageLevelId());
            LanguageAndLevel languageAndLevel = LanguageAndLevel.builder()
                    .languageId(language.getId())
                    .languageLevelId(languageLevel.getId())
                    .build();
            languagesList.add(languageAndLevel);
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
        List<User> byNameOrSurname = userRepository.findByNameOrSurname(key);

        List<UserResponse> users = new LinkedList<>();
        for (User user : byNameOrSurname) {
            UserResponse userResponse = userMapper.map(user);
            users.add(findLanguage(user,userResponse));
        }
        return ResponseEntity.ok(users);
    }

    public UserResponse findLanguage(User user, UserResponse userResponse) {
        List<LanguageAndLevelResponse> languageAndLevelRespons = new LinkedList<>();
        for (LanguageAndLevel languageAndLevel : user.getUserInformation().getLanguages()) {
            Language language = languageRepository.findById(languageAndLevel.getLanguageId()).orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(LANGUAGE_NOT_FOUND.getMessage(), LANGUAGE_NOT_FOUND.getHttpStatus())));
            LanguageLevel languageLevel = languageLevelRepository.findById(languageAndLevel.getLanguageId()).orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(LANGUAGE_LEVEL_NOT_FOUND.getMessage(), LANGUAGE_LEVEL_NOT_FOUND.getHttpStatus())));
            LanguageAndLevelResponse languageAndLevelResponse = LanguageAndLevelResponse.builder()
                    .language(language.getName())
                    .languageLevel(languageLevel.getName())
                    .build();
            languageAndLevelRespons.add(languageAndLevelResponse);
        }
        userResponse.getUserInformation().setProfileImage(fileService.findByName(user.getUserInformation().getProfileImage()));
        userResponse.getUserInformation().setLanguages(languageAndLevelRespons);

        return userResponse;
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByEducationLevel(long educationLevelId) {

        List<User> users = userRepository.findByUserInformation_EducationLevelDetail_EducationLevel_Id(educationLevelId);
        List<UserResponse> usersResponses = new LinkedList<>();
        for (User user : users) {
            UserResponse userResponse = userMapper.map(user);
            usersResponses.add(findLanguage(user,userResponse));
        }
        return ResponseEntity.ok(usersResponses);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByUserInformation_Address(String address) {

        List<User> users = userRepository.findByUserInformation_Address(address);

        List<UserResponse> usersResponses = new LinkedList<>();
        for (User user : users) {
            UserResponse userResponse = userMapper.map(user);
            usersResponses.add(findLanguage(user,userResponse));
        }
        return ResponseEntity.ok(usersResponses);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findByUserInformation_MilitaryQualification_Id(long militaryQualificationId) {
        List<User> users = userRepository.findByUserInformation_MilitaryQualification_Id(militaryQualificationId);

        List<UserResponse> usersResponses = new LinkedList<>();
        for (User user : users) {
            UserResponse userResponse = userMapper.map(user);
            usersResponses.add(findLanguage(user,userResponse));
        }
        return ResponseEntity.ok(usersResponses);
    }
}
