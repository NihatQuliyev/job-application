package com.aztu.job_application.service;


import com.aztu.job_application.model.entity.Otp;
import com.aztu.job_application.model.entity.User;

public interface OtpService {

    void createOtp(User user);
    Otp findByCheckOtp(String username, int otp);
}
