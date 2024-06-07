package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.RoleRequest;
import com.bangvan.demologin.dto.respond.RoleResponse;
import com.bangvan.demologin.entity.Permission;
import com.bangvan.demologin.entity.Role;
import com.bangvan.demologin.exception.AppException;
import com.bangvan.demologin.exception.ErrorCode;
import com.bangvan.demologin.repository.PermissionRepository;
import com.bangvan.demologin.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = modelMapper.map(roleRequest, Role.class);
        role.setPermissions(
                roleRequest
                        .getPermissions()
                        .stream()
                        .map(permissionName ->
                        {
                            Permission permission = permissionRepository.findByName(permissionName);
                            if (permission == null) {
                                throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
                            }
                            return permission;
                        })
                        .collect(Collectors.toSet()));
        roleRepository.save(role );
        RoleResponse roleResponse = modelMapper.map(role, RoleResponse.class);
        return roleResponse;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> modelMapper.map(role, RoleResponse.class)).toList();
    }

    @Override
    public void DeleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

}
