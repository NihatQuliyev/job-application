package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.CompanyRequest;
import com.aztu.job_application.model.dto.response.CompanyResponse;
import com.aztu.job_application.service.CompanyService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> createCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @PostMapping("uploading/{company-id}")
    ResponseEntity<Void> addCompanyImage(@RequestPart("multipart-file") MultipartFile multipartFile,
                                         @PathVariable(name = "company-id") long companyId) {
        return companyService.addCompanyImage(multipartFile,companyId);

    }

    @GetMapping
    ResponseEntity<List<CompanyResponse>> companies() {
        return companyService.findAllCompany();
    }


    @GetMapping("/{id}")
    ResponseEntity<CompanyResponse> findById(@PathVariable long id) {
        return companyService.findById(id);
    }

    //FIXME

}
