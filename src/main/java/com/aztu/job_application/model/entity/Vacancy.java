package com.aztu.job_application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "_vacancy")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Company company;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TableDetail tableDetail;

    @OneToOne(cascade = CascadeType.PERSIST)
    private VacancyDetail vacancyDetail;
}
