package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.CategoryRequest;
import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.model.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<Void> createCategory(CategoryRequest categoryRequest);

    Category getById(long id);

    ResponseEntity<List<CategoryResponse>> categories();

    ResponseEntity<CategoryResponse> findById(long id);
}
