package com.bangvan.demologin.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    @NotEmpty(message = "USERNAME_INVALID")
    private String username;
    @Size(min = 5, max = 20,message = "PASSWORD_INVALID")
    private String password;
    @NotEmpty(message = "FIRSTNAME_INVALID")
    private String firstName;
    @NotEmpty(message = "LASTNAME_INVALID")
    private String lastName;
    private LocalDate birthday;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "EMAIL_INVALID")
    private String email;
    private Set<String> roles;

    public String getPassword() {
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(10);
        return passwordEncoder.encode(password);
    }
}
