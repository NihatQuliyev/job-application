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
import com.aztu.job_application.service.FileService;
import com.aztu.job_application.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.LinkedList;
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
    private final FileService fileService;

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
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return getResponse(vacancies);

    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findByCity(String city) {
        List<Vacancy> allByVacancyDetailCity = vacancyRepository.findAllByVacancyDetail_City(city);
        return getResponse(allByVacancyDetailCity);


    }

    private ResponseEntity<List<VacancyResponse>> getResponse(List<Vacancy> allByVacancyDetailCity) {
        List<VacancyResponse> vacancyResponses = new LinkedList<>();
        for(Vacancy vacancy : allByVacancyDetailCity) {
            VacancyResponse vacancyResponse = vacancyMapper.map(vacancy);
            vacancyResponse.getCompany().setLogo(fileService.findByName(vacancy.getCompany().getLogo()));
            vacancyResponses.add(vacancyResponse);
        }
        return ResponseEntity.ok(vacancyResponses);
    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findByPosition(String position) {
        List<Vacancy> allByVacancyDetailPosition = vacancyRepository.findAllByVacancyDetail_Position(position);
        return getResponse(allByVacancyDetailPosition);

    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findAllByCategory(long id) {
        List<Vacancy> allByCategoryId = vacancyRepository.findAllByCategory_Id(id);
        return getResponse(allByCategoryId);
    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findAllBySalary(BigDecimal salary) {
        List<Vacancy> allByVacancyDetailSalary = vacancyRepository.findAllByVacancyDetail_Salary(salary);
        return getResponse(allByVacancyDetailSalary);
    }

    @Override
    public ResponseEntity<List<VacancyResponse>> findAllByCompanyName(String companyName) {
        List<Vacancy> allByCompanyName = vacancyRepository.findAllByCompany_Name(companyName);
        return getResponse(allByCompanyName);
    }

    @Override
    public Vacancy getById(long vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(NOT_FOUND_VACANCY.getMessage(), HttpStatus.NOT_FOUND)));
    }
}
