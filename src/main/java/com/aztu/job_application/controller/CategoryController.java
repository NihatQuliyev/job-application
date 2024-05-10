package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.CategoryRequest;
import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins/categories")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> categories() {
        return categoryService.categories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable long id) {
        return categoryService.findById(id);
    }

}
