package com.bread.realworld.repository;

import com.bread.realworld.domain.Email;
import com.bread.realworld.domain.User;
import com.bread.realworld.domain.Username;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(Email email);

    boolean existsByUsername(Username username);
}