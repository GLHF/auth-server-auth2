package com.craftsmengroup.paymentsystem.authserver.service.impl;

import com.craftsmengroup.paymentsystem.authserver.aop.Log;
import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationDto;
import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationForm;
import com.craftsmengroup.paymentsystem.authserver.exception.AuthException;
import com.craftsmengroup.paymentsystem.authserver.exception.UserExistException;
import com.craftsmengroup.paymentsystem.authserver.model.User;
import com.craftsmengroup.paymentsystem.authserver.repository.UserRepository;
import com.craftsmengroup.paymentsystem.authserver.service.api.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Log
    public RegistrationDto registration(RegistrationForm registrationForm) throws AuthException {
        if (userRepository.existsByEmail(registrationForm.getEmail())) {
            throw new UserExistException(String.format("User with email %s already exist", registrationForm.getEmail()));
        }
        User user = User.builder()
                .email(registrationForm.getEmail())
                .password(passwordEncoder.encode(registrationForm.getPassword()))
                .firstName(registrationForm.getFirstName())
                .lastName(registrationForm.getLastName())
                .phone(registrationForm.getPhone())
                .build();
        userRepository.save(user);
        return RegistrationDto.from(user);
    }
}