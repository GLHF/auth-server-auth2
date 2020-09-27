package com.craftsmengroup.paymentsystem.authserver.secure.details;

import org.springframework.security.core.GrantedAuthority;

public enum  Authority implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");

    private String name;

    Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
