package com.bread.realworld.repository;

public interface UserRepositoryCustom {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}