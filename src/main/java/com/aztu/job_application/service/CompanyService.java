package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.CompanyRequest;
import com.aztu.job_application.model.dto.response.CompanyResponse;
import com.aztu.job_application.model.entity.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {
    ResponseEntity<Void> createCompany(CompanyRequest companyRequest);

    ResponseEntity<Void> addCompanyImage(MultipartFile multipartFile, long companyId);

    Company getById(long companyId);

    ResponseEntity<List<CompanyResponse>> findAllCompany();

    ResponseEntity<CompanyResponse> findById(long id);
}
