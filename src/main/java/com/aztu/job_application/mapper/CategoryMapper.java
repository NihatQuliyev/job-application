package com.aztu.job_application.mapper;

import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse map(Category category);
}
