package com.aztu.job_application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TableDetail tableDetail;

    @OneToMany(mappedBy = "category")
    private List<Vacancy> vacancies;
}
