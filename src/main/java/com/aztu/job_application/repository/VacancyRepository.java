package com.aztu.job_application.repository;

import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("SELECT v FROM _vacancy v WHERE v.vacancyDetail.city LIKE %:city%")
    List<Vacancy> findAllByVacancyDetail_City(String city);

    @Query("SELECT v FROM _vacancy v WHERE v.vacancyDetail.position LIKE %:position%")
    List<Vacancy> findAllByVacancyDetail_Position(@Param("position") String position);

    List<Vacancy> findAllByCategory_Id(long id);

    @Query("SELECT v FROM _vacancy v WHERE v.vacancyDetail.salary >:salary")
    List<Vacancy> findAllByVacancyDetail_Salary(@Param("salary") BigDecimal salary);

    @Query("SELECT v FROM _vacancy v WHERE v.company.name LIKE %:companyName%")
    List<Vacancy> findAllByCompany_Name(@Param("companyName") String companyName);
}
