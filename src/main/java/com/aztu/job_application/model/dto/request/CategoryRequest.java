package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CategoryRequest {

    @NotBlank(message = "Category name cannot be blank")
    private String name;
}
