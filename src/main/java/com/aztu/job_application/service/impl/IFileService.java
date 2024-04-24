package com.aztu.job_application.service.impl;

import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.aztu.job_application.model.enums.ExceptionMessage.FILE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class IFileService implements FileService {

    private final Path ROOT = Paths.get("upload");
    private final ExceptionService exceptionService;

    @SneakyThrows
    private void init(){
        if (!Files.exists(ROOT)) {
            Files.createDirectory(ROOT);
        }
    }

    @SneakyThrows
    public void upload(MultipartFile multipartFile) {
        init();
        Files.copy(multipartFile.getInputStream(), this.ROOT.resolve(
                        Objects.requireNonNull(
                                multipartFile.getOriginalFilename())));
    }

    @SneakyThrows
    public Path findByName(String image) {
            return Files.walk(ROOT)
                    .filter(path -> Files.isRegularFile(path) && path.getFileName().toString().equals(image))
                    .findFirst()
                    .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(FILE_NOT_FOUND.getMessage(), FILE_NOT_FOUND.getHttpStatus())));
    }
}
