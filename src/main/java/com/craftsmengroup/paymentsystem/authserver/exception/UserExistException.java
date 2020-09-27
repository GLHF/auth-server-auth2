package com.craftsmengroup.paymentsystem.authserver.exception;

import com.craftsmengroup.paymentsystem.authserver.exception.AuthException;

public class UserExistException extends AuthException {
    public UserExistException(String message) {
        super(message);
    }
}
