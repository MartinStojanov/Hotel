package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_reception, ROLE_manager, ROLE_director, ROLE_hostess, ROLE_chef, ROLE_waiter, ROLE_cleaner;

    @Override
    public String getAuthority() {
        return null;
    }
}
