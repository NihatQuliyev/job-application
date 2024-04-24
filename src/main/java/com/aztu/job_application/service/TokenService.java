package com.aztu.job_application.service;


import com.aztu.job_application.model.entity.User;

import java.util.Map;

public interface TokenService {

    void userConfirm(User user);

    void adminConfirm(User user);

    Map<String, User> getByToken(String token);

    void delete(String token);


}
