package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.VacancyRequest;
import com.aztu.job_application.model.dto.response.VacancyResponse;
import com.aztu.job_application.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins/vacancy")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Void> createVacancy(@RequestBody @Valid VacancyRequest vacancyRequest) {

        return vacancyService.creatVacancy(vacancyRequest);
    }

    @GetMapping
    public ResponseEntity<List<VacancyResponse>> findAllVacancies() {
        return vacancyService.findAllVacancies();
    }
    @GetMapping("category/{category-id}")
    public ResponseEntity<List<VacancyResponse>> findAllByCategoryId(@PathVariable(name = "category-id") long id) {
        return vacancyService.findAllByCategory(id);
    }

    @GetMapping("/city/")
    public ResponseEntity<List<VacancyResponse>> findAllByCity(@RequestParam(name = "vacancy-city") String city) {
        return vacancyService.findByCity(city);
    }

    @GetMapping("/position/")
    public ResponseEntity<List<VacancyResponse>> findAllByPosition(@RequestParam(name = "vacancy-position") String position) {
        return vacancyService.findByPosition(position);
    }
}
