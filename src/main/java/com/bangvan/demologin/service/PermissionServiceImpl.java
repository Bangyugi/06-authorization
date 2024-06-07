package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.PermissionRequest;
import com.bangvan.demologin.dto.respond.PermissionResponse;
import com.bangvan.demologin.entity.Permission;
import com.bangvan.demologin.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = modelMapper.map(permissionRequest, Permission.class);
        permission = permissionRepository.save(permission);
        return modelMapper.map(permission, PermissionResponse.class);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permission -> modelMapper.map(permission,PermissionResponse.class)).toList();
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
