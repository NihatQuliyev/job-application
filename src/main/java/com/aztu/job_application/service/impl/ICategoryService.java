package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.CategoryMapper;
import com.aztu.job_application.model.dto.request.CategoryRequest;
import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.model.entity.Category;
import com.aztu.job_application.model.entity.TableDetail;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.CategoryRepository;
import com.aztu.job_application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aztu.job_application.model.enums.ExceptionMessage.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExceptionService exceptionService;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<Void> createCategory(CategoryRequest categoryRequest) {

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .tableDetail(new TableDetail())
                .build();

        categoryRepository.save(category);
        return ResponseEntity.noContent().build();
    }

    public Category getById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(CATEGORY_NOT_FOUND.getMessage(), CATEGORY_NOT_FOUND.getHttpStatus())));
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> categories() {
        List<CategoryResponse> categories = categoryRepository.findAll().stream()
                .map(categoryMapper::map)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<CategoryResponse> findById(long id) {
        CategoryResponse categoryResponse = categoryMapper.map(getById(id));
        return ResponseEntity.ok(categoryResponse);

    }
}
