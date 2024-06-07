package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.PermissionRequest;
import com.bangvan.demologin.dto.respond.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest permissionRequest);

    List<PermissionResponse> getAllPermissions();

    void deletePermission(Long permissionId);
}
