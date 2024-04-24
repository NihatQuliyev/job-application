package com.aztu.job_application.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> role;

    @Override
    public String getAuthority() {
        return name;
    }
    public Permission(String name) {
        this.name = name;
    }
}
