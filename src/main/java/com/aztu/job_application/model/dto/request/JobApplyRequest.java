package com.aztu.job_application.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobApplyRequest {

    private String text;

    private long vacancyId;
}
