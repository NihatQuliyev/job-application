package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.request.RoleRequest;
import com.aztu.job_application.model.dto.response.RoleResponse;
import com.aztu.job_application.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse map(Role role);

    @Mapping(target = "permissions",ignore = true)
    @Mapping(target = "id",ignore = true)
    Role map(RoleRequest roleRequest);
}
