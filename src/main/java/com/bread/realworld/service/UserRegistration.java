package com.bread.realworld.service;

public record UserRegistration(
        String username,
        String email,
        String password
) {
}