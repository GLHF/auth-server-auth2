package com.craftsmengroup.paymentsystem.authserver.secure.details;

import com.craftsmengroup.paymentsystem.authserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecureUser implements UserDetails {
    private String email;
    private String password;
    private boolean banned;
    private List<Authority> authorities;

    public SecureUser(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.banned = user.isBanned();
        this.authorities = Arrays.stream(user.getAuthorities().split(","))
                .map(Authority::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !banned;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !banned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !banned;
    }

    @Override
    public boolean isEnabled() {
        return !banned;
    }
}
