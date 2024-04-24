package com.aztu.job_application.model.dto.response;

import lombok.Getter;

@Getter
public class EmailResponse {

    private String to;

    public void setTo(String to) {
        this.to = to;
    }
}
