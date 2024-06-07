package com.bangvan.demologin.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespond {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private Set<RoleResponse> roles;
}
