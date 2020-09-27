package com.craftsmengroup.paymentsystem.authserver.dto;

import com.craftsmengroup.paymentsystem.authserver.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    public static RegistrationDto from(User user) {
        return RegistrationDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .id(user.getId())
                .build();
    }
}
