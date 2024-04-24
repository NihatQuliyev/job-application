package com.aztu.job_application.model.enums;

import lombok.Getter;

@Getter
public enum SubjectType {

    REGISTRATION("Registration!"),
    FORGET_PASSWORD("Forget password!");

    private final String subject;

    SubjectType(String subject) {
        this.subject = subject;
    }
}
