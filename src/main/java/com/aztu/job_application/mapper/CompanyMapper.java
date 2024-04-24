package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.response.CompanyResponse;
import com.aztu.job_application.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(target = "logo",ignore = true)
    CompanyResponse map(Company company);
}
