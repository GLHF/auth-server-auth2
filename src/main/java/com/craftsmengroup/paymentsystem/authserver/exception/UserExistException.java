package com.craftsmengroup.paymentsystem.authserver.exception;

public class UserExistException extends AuthException {
    public UserExistException(String message) {
        super(message);
    }
}
