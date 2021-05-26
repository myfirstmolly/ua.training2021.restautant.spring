package com.restaurant.api.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    MANAGER(0),
    CUSTOMER(1);

    int id;

    Role(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    @Override
    public String getAuthority() {
        return name();
    }

}
