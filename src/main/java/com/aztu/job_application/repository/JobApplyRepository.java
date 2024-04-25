package com.aztu.job_application.repository;

import com.aztu.job_application.model.entity.JobApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplyRepository extends JpaRepository<JobApply, Long> {
}