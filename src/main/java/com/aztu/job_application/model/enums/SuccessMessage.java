package com.aztu.job_application.model.enums;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum SuccessMessage {

    SUCCESS_SEND_EMAIL("SEND_EMAIL_SUCCESSFULLY"),
    RESET_PASSWORD_SUCCESSFULLY("RESET_PASSWORD_SUCCESSFULLY"),
    CHANGE_PASSWORD_SUCCESSFULLY("Change password successfully!");

    private final String message;


    SuccessMessage(String message) {
        this.message = message;
    }
}
