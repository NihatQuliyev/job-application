package com.aztu.job_application.model.enums;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    ROLE_NOT_FOUND("Role not found!", HttpStatus.NOT_FOUND),
    TOKEN_NOT_FOUND("Token not found", HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED_EXCEPTION("Exception unauthorized token-expired", HttpStatus.UNAUTHORIZED),
    ID_NOT_FOUND_EXCEPTION("Exception id not found", HttpStatus.BAD_REQUEST),
    USER_MUST_NOT_BE_NULL("Exception null pointer user", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_MUST_NOT_BE_NULL("Exception null pointer refresh token", HttpStatus.BAD_REQUEST),
    USER_NULL_POINTER_EXCEPTION("Exception null pointer", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_FOUND("Username not found!", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("Wrong old password", HttpStatus.BAD_REQUEST),
    DIFFER_PASSWORD("Please Enter the equals password!", HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND("Company not found!", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("Category not found !", HttpStatus.NOT_FOUND),
    FILE_NOT_FOUND("Images not found!", HttpStatus.NOT_FOUND),
    GENDER_NOT_FOUND("Gender not found!", HttpStatus.NOT_FOUND),
    MILITARY_QUALIFICATION_EXCEPTION("Military qualifaction not found!",HttpStatus.NOT_FOUND),
    MARITAL_STATUS_EXCEPTION("Marital status not found!", HttpStatus.NOT_FOUND ),
    EDUCATION_LEVEL_NOT_FOUND("Education level not found!", HttpStatus.NOT_FOUND ),
    EMPLOYMENT_STATUS_NOT_FOUND("Employment status not found!", HttpStatus.NOT_FOUND),
    LANGUAGE_NOT_FOUND("Language not found!", HttpStatus.NOT_FOUND),
    LANGUAGE_LEVEL_NOT_FOUND("Language level not found!", HttpStatus.NOT_FOUND),
    NOT_FOUND_VACANCY("Not found vacancy!", HttpStatus.NOT_FOUND);


    private final String message;
    private final HttpStatus httpStatus;

    ExceptionMessage(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
