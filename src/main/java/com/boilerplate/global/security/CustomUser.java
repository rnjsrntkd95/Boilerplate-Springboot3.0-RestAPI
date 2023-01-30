package com.boilerplate.global.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUser implements UserDetails {

    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> authorities;
    private final boolean accountExpiredYn;
    private final boolean accountLockedYn;
    private final boolean credentialsExpiredYn;
    private final boolean enabledYn;

    protected CustomUser(String username, String password, Set<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountExpiredYn = false;
        this.accountLockedYn = false;
        this.credentialsExpiredYn = false;
        this.enabledYn = true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpiredYn;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLockedYn;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpiredYn;
    }

    @Override
    public boolean isEnabled() {
        return enabledYn;
    }
}
