package com.aztu.job_application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String logo;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TableDetail tableDetail;

    @OneToMany(mappedBy = "company")
    public List<Vacancy> vacancy;


}
