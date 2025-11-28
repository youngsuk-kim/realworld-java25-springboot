package com.bread.realworld.repository;

import com.bread.realworld.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bread.realworld.domain.QUser.user;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return Optional.ofNullable(
                from(user)
                        .where(user.email.email.eq(email))
                        .limit(1)
                        .fetchOne()
        ).isPresent();
    }

    @Override
    public boolean existsByUsername(final String username) {
        return Optional.ofNullable(
                from(user)
                        .where(user.username.username.eq(username))
                        .limit(1)
                        .fetchOne()
        ).isPresent();
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return Optional.ofNullable(
                from(user)
                        .where(user.username.username.eq(username))
                        .fetchOne()
        );
    }

}
