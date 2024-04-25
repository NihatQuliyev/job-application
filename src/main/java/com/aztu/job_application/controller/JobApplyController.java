package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.JobApplyRequest;
import com.aztu.job_application.service.JobApplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/users/job-apply")
@RequiredArgsConstructor
public class JobApplyController {

    private final JobApplyService jobApplyService;

    @PostMapping
    public ResponseEntity<Void> jobApply(@RequestBody @Valid JobApplyRequest jobApplyRequest) {
        return jobApplyService.jobApply(jobApplyRequest);
    }

    @PostMapping("uploading/{job-apply-id}")
    ResponseEntity<Void> addJobApplyCV(@RequestPart("multipart-file") MultipartFile multipartFile,
                                         @PathVariable(name = "job-apply-id") long jobApplyId) {
        return jobApplyService.addCvToJobApply(multipartFile, jobApplyId);
    }
}
