package com.bangvan.demologin.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    private Long id;
    private String name;
    private String description;
}
