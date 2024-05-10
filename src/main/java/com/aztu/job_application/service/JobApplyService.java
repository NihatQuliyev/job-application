package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.JobApplyRequest;
import com.aztu.job_application.model.dto.response.JobApplyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobApplyService  {

    ResponseEntity<Void> jobApply(JobApplyRequest jobApplyRequest, MultipartFile multipartFile);

    ResponseEntity<List<JobApplyResponse>> findAll();
}
