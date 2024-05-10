package com.aztu.job_application.model.dto.response.userInformation;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LanguageAndLevelResponse {

    private String language;

    private String languageLevel;
}
