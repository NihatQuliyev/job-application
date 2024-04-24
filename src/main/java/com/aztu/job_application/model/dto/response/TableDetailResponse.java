package com.aztu.job_application.model.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TableDetailResponse {

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean status;
}
