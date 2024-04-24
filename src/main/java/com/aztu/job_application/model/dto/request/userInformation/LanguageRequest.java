package com.aztu.job_application.model.dto.request.userInformation;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LanguageRequest {
    @NotNull
    private int languageId;

    @NotNull
    private int languageLevelId;
}
