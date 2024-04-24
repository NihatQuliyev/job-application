package com.aztu.job_application.model.dto.response;

import lombok.*;

import java.nio.file.Path;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {

    private long id;

    private String name;

    private Path logo;

    private TableDetailResponse tableDetail;

}
