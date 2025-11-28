package com.bread.realworld.service;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException() {
        super("Username already exists");
    }
}