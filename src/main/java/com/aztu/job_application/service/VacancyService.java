package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.VacancyRequest;
import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.model.entity.Vacancy;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyService {
    ResponseEntity<Void> creatVacancy(VacancyRequest vacancyRequest);

    ResponseEntity<List<VacancyResponse>> findAllVacancies();

    ResponseEntity<List<VacancyResponse>> findByCity(String city);

    ResponseEntity<List<VacancyResponse>> findByPosition(String position);

    ResponseEntity<List<VacancyResponse>> findAllByCategory(long id);

    ResponseEntity<List<VacancyResponse>> findAllBySalary(BigDecimal salary);

    ResponseEntity<List<VacancyResponse>> findAllByCompanyName(String companyName);

    Vacancy getById(long vacancyId);
}
