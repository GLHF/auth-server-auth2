package com.craftsmengroup.paymentsystem.authserver.service.api;

import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationDto;
import com.craftsmengroup.paymentsystem.authserver.dto.RegistrationForm;
import com.craftsmengroup.paymentsystem.authserver.exception.AuthException;

public interface AuthService {
    RegistrationDto registration(RegistrationForm registrationForm) throws AuthException;
}