package com.aztu.job_application.helper;

import com.aztu.job_application.model.dto.request.EmailRequest;
import com.aztu.job_application.model.entity.Otp;
import com.aztu.job_application.model.entity.Token;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.SubjectType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    @Value("${application.mail.http}")
    String http;

    @Value("${application.mail.host}")
    String host;

    @Value("${application.mail.user-confirmation}")
    String userConfirmation;

    @Value("${application.mail.admin-confirmation}")
    String adminConfirmation;

    @Value("${application.mail.reset-password}")
    String resetPassword;


    public EmailRequest sendEmailToVerifyUser(Token token, User user) {
        String url =http + host + getServerPort() + userConfirmation + token.getToken();

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectType.REGISTRATION.getSubject())
                .text("<p> Hi, " + user.getName() + ", <p>" +
                        "<p>Thank you for registering with us," +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public EmailRequest sendEmailToVerifyAdmin(Token token, User user) {
        String url =http + host + getServerPort() + adminConfirmation + token.getToken();

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectType.REGISTRATION.getSubject())
                .text("<p> Hi, " + user.getName() + ", <p>" +
                        "<p>Thank you for registering with us," +
                        "Please, follow the link and verify password below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Admin Registration Portal Service")
                .build();
    }

    public EmailRequest generateEmailRequest(Otp otp, User user) {
        String url =http + host + getServerPort() +resetPassword + user.getEmail() + "&otp=" + otp.getOtp();

        String text = "<p> Hi, " + user.getEmail() + ", <p>" +
                "<p>Thank you for reset password with us," +
                "Please, follow the link below to complete your reset password.<p>" +
                "<a href=\"" + url + "\">Verify your email to reset password<a>" +
                "<p> Thank you <br> Users Reset password Portal Service";

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectType.FORGET_PASSWORD.getSubject())
                .text(text)
                .build();
    }


    private String getServerPort(){
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder
                        .getRequestAttributes()))
                .getRequest();

        return  ":" + httpServletRequest.getServerPort();
    }
}
