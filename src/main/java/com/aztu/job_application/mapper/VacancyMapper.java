package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.request.VacancyRequest;
import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.model.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    @Mapping(target = "category",ignore = true)
    @Mapping(target = "company",ignore = true)
    Vacancy map(VacancyRequest vacancyRequest);

    @Mapping(target = "company.logo",ignore = true)
    VacancyResponse map(Vacancy vacancy);
}
