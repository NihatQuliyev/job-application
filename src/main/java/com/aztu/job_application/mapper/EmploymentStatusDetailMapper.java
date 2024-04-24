package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.request.userInformation.EmploymentStatusDetailRequest;
import com.aztu.job_application.model.entity.userInformation.EmploymentStatusDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmploymentStatusDetailMapper {
    EmploymentStatusDetail map(EmploymentStatusDetailRequest employmentStatusDetailRequest);
}
