package com.craftsmengroup.paymentsystem.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
}
