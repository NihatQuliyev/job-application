package com.aztu.job_application.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


public interface FileService {
    void upload(MultipartFile multipartFile);

    Path findByName(String imageName);
}
