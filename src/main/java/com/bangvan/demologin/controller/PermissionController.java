package com.bangvan.demologin.controller;

import com.bangvan.demologin.dto.request.ApiResponse;
import com.bangvan.demologin.dto.request.PermissionRequest;
import com.bangvan.demologin.dto.respond.PermissionResponse;
import com.bangvan.demologin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
        var apiResponse = new ApiResponse<PermissionResponse>();
        apiResponse.setCode(200);
        apiResponse.setResult(permissionService.createPermission(permissionRequest));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermissions() {
        var apiResponse = new ApiResponse<List<PermissionResponse>>();
        apiResponse.setCode(200);
        apiResponse.setResult(permissionService.getAllPermissions());
        return apiResponse;
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<String> deletePermission(@PathVariable("permissionId") Long permissionId) {
        var apiResponse = new ApiResponse<String>();
        apiResponse.setCode(200);
        permissionService.deletePermission(permissionId);
        apiResponse.setMessage("Permission deleted successfully.");
        return apiResponse;
    }
}
