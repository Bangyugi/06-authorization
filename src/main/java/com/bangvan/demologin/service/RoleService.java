package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.RoleRequest;
import com.bangvan.demologin.dto.respond.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);

    List<RoleResponse> getAllRoles();

    void DeleteRole(Long roleId);
}
