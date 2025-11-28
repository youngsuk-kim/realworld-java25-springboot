package com.bread.realworld.service;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super("Email already exists");
    }
}