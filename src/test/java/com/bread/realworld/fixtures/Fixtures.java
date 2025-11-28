package com.bread.realworld.fixtures;

import com.bread.realworld.domain.Email;
import com.bread.realworld.domain.Password;
import com.bread.realworld.domain.User;
import com.bread.realworld.domain.Username;
import com.bread.realworld.service.UserRegistration;

public class Fixtures {

    public static User.UserBuilder aUser() {
        return User.builder()
                .id(1L)
                .username(aUsername())
                .email(aEmail())
                .password(new Password("encodedPassword"));
    }

    public static Username aUsername() {
        return new Username("testuser");
    }

    public static Email aEmail() {
        return new Email("test@test.com");
    }

    public static Password aPassword() {
        return new Password("rawPassword");
    }

    public static UserRegistration aRegisterUser() {
        return new UserRegistration("testuser", "test@test.com", "rawPassword");
    }
}