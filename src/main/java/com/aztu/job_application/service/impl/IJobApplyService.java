package com.aztu.job_application.service.impl;

import com.aztu.job_application.model.dto.request.JobApplyRequest;
import com.aztu.job_application.model.entity.JobApply;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.entity.Vacancy;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.JobApplyRepository;
import com.aztu.job_application.service.AuthService;
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.JobApplyService;
import com.aztu.job_application.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.aztu.job_application.model.enums.ExceptionMessage.JOB_APPLY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IJobApplyService implements JobApplyService {

    private final VacancyService vacancyService;
    private final AuthService authService;
    private final JobApplyRepository jobApplyRepository;
    private final ExceptionService exceptionService;
    private final FileService fileService;

    @Override
    public ResponseEntity<Void> jobApply(JobApplyRequest jobApplyRequest) {
        Vacancy vacancy =  vacancyService.getById(jobApplyRequest.getVacancyId());
        User user = authService.getAuthenticatedUser();
        JobApply jobApply = JobApply.builder()
                .text(jobApplyRequest.getText())
                .vacancy(vacancy)
                .user(user)
                .build();
        jobApplyRepository.save(jobApply);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> addCvToJobApply(MultipartFile multipartFile, long jobApplyId) {
        JobApply jobApply = getById(jobApplyId);
        String cvName  = multipartFile.getOriginalFilename();
        jobApply.setCv(cvName);
        fileService.upload(multipartFile);
        return null;
    }

    private JobApply getById(long jobApplyId) {
        return jobApplyRepository.findById(jobApplyId)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(JOB_APPLY_NOT_FOUND.getMessage(), JOB_APPLY_NOT_FOUND.getHttpStatus())));
    }
}
