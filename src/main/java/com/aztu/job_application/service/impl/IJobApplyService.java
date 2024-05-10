package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.JobApplyMapper;
import com.aztu.job_application.model.dto.request.JobApplyRequest;
import com.aztu.job_application.model.dto.response.JobApplyResponse;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.JobApply;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.entity.Vacancy;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.JobApplyRepository;
import com.aztu.job_application.service.AuthService;
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.JobApplyService;
import com.aztu.job_application.service.VacancyService;
import com.aztu.job_application.service.userInformation.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.aztu.job_application.model.enums.ExceptionMessage.JOB_APPLY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IJobApplyService implements JobApplyService {

    private final VacancyService vacancyService;
    private final AuthService authService;
    private final JobApplyRepository jobApplyRepository;
    private final ExceptionService exceptionService;
    private final FileService fileService;
    private final JobApplyMapper jobApplyMapper;
    private final UserInformationService userInformationService;

    @Override
    public ResponseEntity<Void> jobApply(JobApplyRequest jobApplyRequest, MultipartFile multipartFile) {
        Vacancy vacancy =  vacancyService.getById(jobApplyRequest.getVacancyId());
        User user = authService.getAuthenticatedUser();
        String cvName  = UUID.randomUUID() + ".pdf";
        JobApply jobApply = JobApply.builder()
                .text(jobApplyRequest.getText())
                .vacancy(vacancy)
                .user(user)
                .cv(cvName)
                .build();

        jobApplyRepository.save(jobApply);
        fileService.upload(multipartFile, cvName);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<JobApplyResponse>> findAll() {
        List<JobApply> jobApplies = jobApplyRepository.findAll();

        List<JobApplyResponse> jobApplyResponses = new LinkedList<>();
        for (JobApply jobApply : jobApplies) {
            JobApplyResponse jobApplyResponse = jobApplyMapper.map(jobApply);
            User user = jobApply.getUser();
            jobApplyResponse.getUser().getUserInformation().setProfileImage(fileService.findByName(user.getUserInformation().getProfileImage()));
            jobApplyResponse.setCv(fileService.findByName(jobApply.getCv()));

            UserResponse updateUserResponse = userInformationService.findLanguage(user, jobApplyResponse.getUser());
            updateUserResponse.getUserInformation().setProfileImage(fileService.findByName(jobApply.getUser().getUserInformation().getProfileImage()));
            jobApplyResponse.setUser(updateUserResponse);
            jobApplyResponse.getVacancy().getCompany().setLogo(fileService.findByName(jobApply.getVacancy().getCompany().getLogo()));
            jobApplyResponses.add(jobApplyResponse);

        }
        return ResponseEntity.ok(jobApplyResponses);
    }

    private JobApply getById(long jobApplyId) {
        return jobApplyRepository.findById(jobApplyId)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(JOB_APPLY_NOT_FOUND.getMessage(), JOB_APPLY_NOT_FOUND.getHttpStatus())));
    }
}
