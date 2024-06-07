package com.bangvan.demologin.controller;

import com.bangvan.demologin.dto.request.ApiResponse;
import com.bangvan.demologin.dto.request.RoleRequest;
import com.bangvan.demologin.dto.respond.RoleResponse;
import com.bangvan.demologin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        var apiResponse = new ApiResponse<RoleResponse>();
        apiResponse.setCode(200);
        apiResponse.setResult(roleService.createRole(roleRequest));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles(){
        var apiResponse = new ApiResponse<List<RoleResponse>>();
        apiResponse.setCode(200);
        apiResponse.setResult(roleService.getAllRoles());
        return apiResponse;
    }

    @DeleteMapping("/{roleId}")
    public ApiResponse<String> deleteRole(@PathVariable("roleId") Long roleId){
        var apiResponse = new ApiResponse<String>();
        apiResponse.setCode(200);
        roleService.DeleteRole(roleId);
        apiResponse.setMessage("Role deleted successfully");
        return apiResponse;
    }

}
