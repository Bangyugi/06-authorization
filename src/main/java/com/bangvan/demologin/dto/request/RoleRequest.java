package com.bangvan.demologin.dto.request;

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
public class RoleRequest {
    private Long id;
    private String role;
    private Set<String> permissions;

    @Override
    public String toString() {
        return "RoleRequest{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
