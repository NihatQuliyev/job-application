package com.aztu.job_application.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Getter
@Setter
public class EmailRequest {

    private String to;
    private String subject;
    private String text;

}
