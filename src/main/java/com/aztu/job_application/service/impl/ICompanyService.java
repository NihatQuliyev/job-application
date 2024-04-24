package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.CompanyMapper;
import com.aztu.job_application.model.dto.request.CompanyRequest;
import com.aztu.job_application.model.dto.response.CompanyResponse;
import com.aztu.job_application.model.entity.Company;
import com.aztu.job_application.model.entity.TableDetail;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.CompanyRepository;
import com.aztu.job_application.service.CompanyService;
import com.aztu.job_application.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.LinkedList;
import java.util.List;

import static com.aztu.job_application.model.enums.ExceptionMessage.COMPANY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ICompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ExceptionService exceptionService;
    private final FileService fileService;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseEntity<Void> createCompany(CompanyRequest companyRequest) {

        Company company = Company.builder()
                .name(companyRequest.getName())
                .tableDetail(new TableDetail())
                .build();
        companyRepository.save(company);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> addCompanyImage(MultipartFile multipartFile, long companyId) {
        Company company = getById(companyId);
        String logo  = multipartFile.getOriginalFilename();
        company.setLogo(logo);
        fileService.upload(multipartFile);
        return ResponseEntity.noContent().build();
    }

    public Company getById(long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(COMPANY_NOT_FOUND.getMessage(),COMPANY_NOT_FOUND.getHttpStatus())));
    }



    @Override
    public ResponseEntity<List<CompanyResponse>> findAllCompany() {

        List<Company> companies = companyRepository.findAll();
        List<CompanyResponse> companyResponses = new LinkedList<>();

        for (Company company : companies) {
            CompanyResponse companyResponse = companyMapper.map(company);
            companyResponse.setLogo(fileService.findByName(company.getLogo()));
            companyResponses.add(companyResponse);
        }
        return ResponseEntity.ok(companyResponses);

    }

    @Override
    public ResponseEntity<CompanyResponse> findById(long id) {
        Company company = getById(id);
        CompanyResponse companyResponse = companyMapper.map(company);
        companyResponse.setLogo(fileService.findByName(company.getLogo()));

        return ResponseEntity.ok(companyResponse);
    }

}

