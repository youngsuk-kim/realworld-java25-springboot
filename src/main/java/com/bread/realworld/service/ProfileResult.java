package com.bread.realworld.service;

import com.bread.realworld.domain.User;

public record ProfileResult(
        String username,
        String bio,
        String image,
        boolean following
) {
    public ProfileResult(final User user, final boolean following) {
        this(
                user.getUserName(),
                user.getBio(),
                user.getImage(),
                following
        );
    }
}