package com.aztu.job_application.controller;

import com.aztu.job_application.mapper.JobApplyMapper;
import com.aztu.job_application.model.dto.request.JobApplyRequest;
import com.aztu.job_application.model.dto.response.JobApplyResponse;
import com.aztu.job_application.model.entity.JobApply;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.repository.JobApplyRepository;
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.JobApplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/job-apply")
@RequiredArgsConstructor
public class JobApplyController {

    private final JobApplyService jobApplyService;


    @PostMapping
    public ResponseEntity<Void> jobApply(@RequestPart("data") @Valid JobApplyRequest jobApplyRequest,
                                         @RequestPart("cv") MultipartFile multipartFile) {
        return jobApplyService.jobApply(jobApplyRequest, multipartFile);
    }

    @GetMapping
    public ResponseEntity<List<JobApplyResponse>> jobApplies() {

        return jobApplyService.findAll();
    }
}
