package com.craftsmengroup.paymentsystem.authserver.controller;

import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationDto;
import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationForm;
import com.craftsmengroup.paymentsystem.authserver.exception.AuthException;
import com.craftsmengroup.paymentsystem.authserver.exception.UserExistException;
import com.craftsmengroup.paymentsystem.authserver.service.api.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Регистрация пользователя
     * */
    @PostMapping("/registration")
    public ResponseEntity registration(@Validated @RequestBody RegistrationForm registrationForm) {
        try {
            RegistrationDto registration = authService.registration(registrationForm);
            return ResponseEntity.ok(registration);
        } catch (UserExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with login already exist");
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration is failure");
        }
    }
}
