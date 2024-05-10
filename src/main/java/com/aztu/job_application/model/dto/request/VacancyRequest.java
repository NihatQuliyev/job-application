package com.aztu.job_application.model.dto.request;

import jakarta.validation.Valid;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyRequest {

    private long categoryId;

    private long companyId;

    @Valid
    private VacancyDetailRequest vacancyDetail;

}
