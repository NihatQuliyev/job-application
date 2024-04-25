package com.aztu.job_application.repository;

import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByVacancyDetail_City(String city);

    List<Vacancy> findAllByVacancyDetail_Position(String position);

    List<Vacancy> findAllByCategory_Id(long id);

    List<Vacancy> findAllByVacancyDetail_Salary(BigDecimal salary);

    List<VacancyResponse> findAllByCompany_Name(String companyName);
}
