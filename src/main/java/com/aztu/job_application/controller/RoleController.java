package com.aztu.job_application.controller;

import com.aztu.job_application.model.dto.request.RoleRequest;
import com.aztu.job_application.model.dto.response.RoleResponse;
import com.aztu.job_application.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admins/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid RoleRequest roleRequest) {

        return roleService.create(roleRequest);
    }

    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody @Valid RoleRequest roleRequest) {

        return roleService.update(roleRequest);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> roles() {

        return roleService.findAllRoles();
    }
}
