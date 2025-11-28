package com.bread.realworld.service;

import com.bread.realworld.domain.Bio;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String username) {
        super("User not found: " + username);
    }
}

