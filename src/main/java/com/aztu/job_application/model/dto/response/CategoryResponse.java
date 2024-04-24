package com.aztu.job_application.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {

    private long id;
    private String name;

    private TableDetailResponse tableDetail;

}
