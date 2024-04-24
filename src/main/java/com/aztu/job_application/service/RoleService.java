package com.aztu.job_application.service;

import com.aztu.job_application.model.dto.request.RoleRequest;
import com.aztu.job_application.model.dto.response.RoleResponse;
import com.aztu.job_application.model.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    ResponseEntity<Void> create(RoleRequest roleRequest);

    Role findByRole(String name);


    ResponseEntity<Void> update(RoleRequest roleRequest);

    ResponseEntity<List<RoleResponse>> findAllRoles();
}
