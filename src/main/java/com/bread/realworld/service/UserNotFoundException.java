package com.bread.realworld.service;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String username) {
        super("User not found: " + username);
    }
}

