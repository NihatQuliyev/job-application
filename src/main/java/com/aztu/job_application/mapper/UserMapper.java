package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.request.AdminRequest;
import com.aztu.job_application.model.dto.request.UserRequest;
import com.aztu.job_application.model.dto.response.UserResponse;
import com.aztu.job_application.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRequest userRequest);

    User map(AdminRequest adminRequest);

    @Mapping(target = "userInformation.profileImage", ignore = true)
    UserResponse map(User user);
}
