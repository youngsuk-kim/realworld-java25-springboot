package com.bread.realworld.repository;

import com.bread.realworld.domain.Follow;

import java.util.Optional;

public interface FollowRepositoryCustom {
    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    Optional<Follow> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
}