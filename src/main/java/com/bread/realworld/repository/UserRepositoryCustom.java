package com.bread.realworld.repository;

import com.bread.realworld.domain.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}