package com.aztu.job_application.model.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyResponse {

    private CategoryResponse category;

    private CompanyResponse company;

    private VacancyDetailResponse vacancyDetail;

    private TableDetailResponse tableDetail;
}
