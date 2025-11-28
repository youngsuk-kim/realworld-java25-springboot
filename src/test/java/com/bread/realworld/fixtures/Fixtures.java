package com.bread.realworld.fixtures;

import com.bread.realworld.domain.Bio;
import com.bread.realworld.domain.Email;
import com.bread.realworld.domain.Follow;
import com.bread.realworld.domain.Image;
import com.bread.realworld.domain.Password;
import com.bread.realworld.domain.User;
import com.bread.realworld.domain.Username;
import com.bread.realworld.service.UserRegistration;

import java.time.LocalDateTime;

public class Fixtures {

    public static User.UserBuilder aUser() {
        return User.builder()
                .id(1L)
                .username(aUsername())
                .email(aEmail())
                .password(new Password("encodedPassword"))
                .bio(new Bio("test bio"))
                .image(new Image("https://test.com/image.jpg"));
    }

    public static Username aUsername() {
        return new Username("testuser");
    }

    public static Email aEmail() {
        return new Email("test@test.com");
    }

    public static UserRegistration aRegisterUser() {
        return new UserRegistration("testuser", "test@test.com", "rawPassword");
    }

    public static Follow.FollowBuilder aFollow() {
        return Follow.builder()
                .id(1L)
                .followerId(1L)
                .followeeId(2L)
                .followedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
    }
}