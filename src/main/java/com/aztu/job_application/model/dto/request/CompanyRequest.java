package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.aztu.job_application.model.constant.ValidationConstant.NAME_REGEX;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {

    @NotBlank(message = NAME_REGEX)
    private String name;

}
