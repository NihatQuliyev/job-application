package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.JobApplyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface JobApplyService  {

    ResponseEntity<Void> jobApply(JobApplyRequest jobApplyRequest);

    ResponseEntity<Void> addCvToJobApply(MultipartFile multipartFile, long jobApplyId);
}
