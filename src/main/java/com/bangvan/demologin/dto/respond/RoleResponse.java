package com.bangvan.demologin.dto.respond;

import com.bangvan.demologin.dto.request.PermissionRequest;
import com.bangvan.demologin.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private String role;
    private Set<PermissionResponse> permissions;
}
