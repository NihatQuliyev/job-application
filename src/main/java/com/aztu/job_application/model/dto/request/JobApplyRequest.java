package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobApplyRequest {

    @NotBlank(message = "Text cannot be blank")
    private String text;

    private long vacancyId;
}
