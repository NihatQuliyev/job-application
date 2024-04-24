package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.request.userInformation.UserInformationRequest;
import com.aztu.job_application.model.dto.response.userInformation.UserInformationResponse;
import com.aztu.job_application.model.entity.userInformation.UserInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInformationMapper {
    @Mapping(target = "employmentStatusDetail",ignore = true)
    UserInformation map(UserInformationRequest userInformation);

    @Mapping(target = "profileImage", ignore = true)
    UserInformationResponse map(UserInformation userInformation);
}
