package com.aztu.job_application.service.impl;

import com.aztu.job_application.mapper.RoleMapper;
import com.aztu.job_application.model.dto.request.RoleRequest;
import com.aztu.job_application.model.dto.response.RoleResponse;
import com.aztu.job_application.model.entity.Permission;
import com.aztu.job_application.model.entity.Role;
import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.RoleRepository;
import com.aztu.job_application.service.PermissionService;
import com.aztu.job_application.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IRoleService implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final ExceptionService exceptionService;

    @Override
    public ResponseEntity<Void> create(RoleRequest roleRequest) {
        Role role =  roleMapper.map(roleRequest);
        List<Permission> permissions = permissionService.permissions(roleRequest.getPermissions());
        if (permissions != null) {
            role.setPermissions(permissions);
        }else {
            role.setPermissions(new LinkedList<>());
        }
        roleRepository.save(role);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public Role findByRole(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(ExceptionMessage.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
    }

    @Override
    public ResponseEntity<Void> update(RoleRequest roleRequest) {
        Role role = findByRole(roleRequest.getName());
        List<Permission> permissions = permissionService.permissions(roleRequest.getPermissions());
        if (permissions != null) {
            role.setPermissions(permissions);
        }
        roleRepository.save(role);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<RoleResponse>> findAllRoles() {
        return ResponseEntity.ok().body(roleRepository.findAll().stream()
                .map(roleMapper::map)
                .collect(Collectors.toList()));
    }
}
