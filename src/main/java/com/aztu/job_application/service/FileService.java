package com.aztu.job_application.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


public interface FileService {
    void upload(MultipartFile multipartFile, String imageName);

    Path findByName(String imageName);

    void deleteFile(String profileImage);
}
