package com.aztu.job_application.service.impl;

import com.aztu.job_application.model.entity.Permission;
import com.aztu.job_application.repository.PermissionRepository;
import com.aztu.job_application.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionService implements PermissionService {

    private final PermissionRepository permissionRepository;

    public IPermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public List<Permission> permissions(List<Long> permissionIds) {

        if (permissionIds != null) {
            return permissionRepository.findAllById(permissionIds);
        }
        return null;
    }
}
