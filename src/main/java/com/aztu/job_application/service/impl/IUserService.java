package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.UserMapper;
import com.aztu.job_application.model.dto.request.*;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.*;
import com.aztu.job_application.model.entity.userInformation.UserInformation;
import com.aztu.job_application.model.enums.RoleType;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.UserRepository;
import com.aztu.job_application.service.*;
import com.aztu.job_application.service.userInformation.UserInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.aztu.job_application.model.enums.ExceptionMessage.*;
import static com.aztu.job_application.model.enums.SuccessMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IUserService implements UserService {

    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final OtpService otpService;
    private final ExceptionService exceptionService;
    private final AuthService authService;
    private final UserInformationService userInformationService;
    private final FileService fileService;


    @Override
    @Transactional
    public ResponseEntity<Void> userRegistration(UserRequest userRequest, MultipartFile multipartFile) {
        User mappedUser = userMapper.map(userRequest);
        UserInformation userInformation = userInformationService.buildUserInformation(userRequest);
        mappedUser.setUserInformation(userInformation);

        String imageName = UUID.randomUUID() + ".jpg";
        mappedUser.getUserInformation().setProfileImage(imageName);

        User user = registration(mappedUser, RoleType.USER, multipartFile);
        tokenService.userConfirm(user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> userConfirmation(String token) {
        User user = getConfirmUser(token);
        user.setEnable(true);
        userRepository.save(user);
        tokenService.delete(token);

        return ResponseEntity.noContent().build();
    }

    public User getConfirmUser(String token) {
        Map<String, User> byToken = tokenService.getByToken(token);
        long id = byToken.get(token).getId();
        return findById(id);
    }

    private User findById(long id) {

        return userRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(USERNAME_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
    }


    @Override
    public ResponseEntity<String> userRenewPassword(String username) {
        User user = findByUsername(username).getBody();
        otpService.createOtp(user);

        return ResponseEntity.ok(SUCCESS_SEND_EMAIL.getMessage());
    }

    @Override
    @Transactional
    public ResponseEntity<String> resetPassword(String username,
                                                int otp,
                                                PasswordRequest updatePassword) {
        Otp checkOtp = otpService.findByCheckOtp(username, otp);
        checkOtp.getUser().setPassword(passwordEncoder.encode(updatePassword.getUpdatePassword()));

        return ResponseEntity.ok(RESET_PASSWORD_SUCCESSFULLY.getMessage());
    }

    public ResponseEntity<User> findByUsername(String username) {

        return ResponseEntity.ok().body(userRepository
                .findByEmailAndIsEnableTrue(username)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(USERNAME_NOT_FOUND.getMessage(), USERNAME_NOT_FOUND.getHttpStatus()))));
    }

    @Override
    @Transactional
    public ResponseEntity<String> userChangePassword(ChangePasswordRequest changePasswordRequest) {
        User user = authService.getAuthenticatedUser();
        boolean isTruePassword = passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword());
        if (!isTruePassword){
           throw new ApplicationException(exceptionService.exceptionResponse(WRONG_PASSWORD.getMessage(), WRONG_PASSWORD.getHttpStatus()));
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        return ResponseEntity.ok(CHANGE_PASSWORD_SUCCESSFULLY.getMessage());
    }

    public User registration(User user, RoleType roleType, MultipartFile multipartFile){
        Role role = roleService.findByRole(roleType.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        TableDetail tableDetail = new TableDetail();
        user.setTableDetail(tableDetail);
        Optional<User> byEmailAndIsEnableTrue = userRepository.findByEmail(user.getEmail());
        if (byEmailAndIsEnableTrue.isPresent()){
            throw new ApplicationException(exceptionService.exceptionResponse(EMAIL_ALREADY.getMessage(), EMAIL_ALREADY.getHttpStatus()));
        }
        userRepository.save(user);
        fileService.upload(multipartFile, user.getUserInformation().getProfileImage());
        return user;
    }



    private User getById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(USERNAME_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new LinkedList<>();
        for (User user : users) {
            UserResponse userResponse = userMapper.map(user);
            userResponses.add(userInformationService.findLanguage(user,userResponse));
        }

        return ResponseEntity.ok(userResponses);
    }
}
