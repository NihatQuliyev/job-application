package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.VacancyMapper;
import com.aztu.job_application.model.dto.request.VacancyRequest;
import com.aztu.job_application.model.dto.response.CategoryResponse;
import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.model.entity.Category;
import com.aztu.job_application.model.entity.Company;
import com.aztu.job_application.model.entity.TableDetail;
import com.aztu.job_application.model.entity.Vacancy;
import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.VacancyRepository;
import com.aztu.job_application.service.CategoryService;
import com.aztu.job_application.service.CompanyService;
import com.aztu.job_application.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aztu.job_application.model.enums.ExceptionMessage.NOT_FOUND_VACANCY;

@Service
@RequiredArgsConstructor
public class IVacancyService implements VacancyService {

    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final VacancyMapper vacancyMapper;
    private final VacancyRepository vacancyRepository;
    private final ExceptionService exceptionService;

    @Override
    public ResponseEntity<Void> creatVacancy(VacancyRequest vacancyRequest) {
        Category category = categoryService.getById(vacancyRequest.getCategoryId());
        Company company = companyService.getById(vacancyRequest.getCompanyId());
        Vacancy vacancy = vacancyMapper.map(vacancyRequest);
        vacancy.setCategory(category);
        vacancy.setCompany(company);
        vacancy.setTableDetail(new TableDetail());
        vacancyRepository.save(vacancy);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findAllVacancies() {
        return ResponseEntity.ok(vacancyRepository.findAll().stream()
                .map(vacancyMapper::map)
                .toList());

    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findByCity(String city) {
       return ResponseEntity.ok(vacancyRepository.findAllByVacancyDetail_City(city).stream()
               .map(vacancyMapper::map)
               .toList());

    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findByPosition(String position) {
        return ResponseEntity.ok(vacancyRepository.findAllByVacancyDetail_Position(position).stream()
                .map(vacancyMapper::map)
                .toList());

    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findAllByCategory(long id) {
        return ResponseEntity.ok(vacancyRepository.findAllByCategory_Id(id).stream()
                .map(vacancyMapper::map)
                .toList());
    }
}
