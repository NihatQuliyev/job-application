package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.response.JobApplyResponse;
import com.aztu.job_application.model.entity.JobApply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobApplyMapper {

    @Mapping(target = "cv", ignore = true)
    @Mapping(target = "vacancy.company.logo",ignore = true)
    @Mapping(target = "user.userInformation.profileImage", ignore = true)
    JobApplyResponse map(JobApply jobApply);
}
