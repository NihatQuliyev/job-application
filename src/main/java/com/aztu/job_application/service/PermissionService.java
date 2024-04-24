package com.aztu.job_application.service;


import com.aztu.job_application.model.entity.Permission;

import java.util.List;


public interface PermissionService {

    List<Permission> permissions(List<Long> permissionIds);
}
