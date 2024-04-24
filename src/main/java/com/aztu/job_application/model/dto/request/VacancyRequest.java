package com.aztu.job_application.model.dto.request;

import com.aztu.job_application.model.dto.response.TableDetailResponse;
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

    private VacancyDetailRequest vacancyDetail;

}
