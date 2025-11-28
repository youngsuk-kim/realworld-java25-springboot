package com.bread.realworld.repository;

import com.bread.realworld.domain.Follow;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bread.realworld.domain.QFollow.follow;

@Repository
public class FollowRepositoryImpl extends QuerydslRepositorySupport implements FollowRepositoryCustom {

    public FollowRepositoryImpl() {
        super(Follow.class);
    }

    @Override
    public boolean existsByFollowerIdAndFolloweeId(final Long followerId, final Long followeeId) {
        return Optional.ofNullable(
                from(follow)
                        .where(follow.followerId.eq(followerId)
                                .and(follow.followeeId.eq(followeeId)))
                        .limit(1)
                        .fetchOne()
        ).isPresent();
    }

    @Override
    public Optional<Follow> findByFollowerIdAndFolloweeId(final Long followerId, final Long followeeId) {
        return Optional.ofNullable(
                from(follow)
                        .where(follow.followerId.eq(followerId)
                                .and(follow.followeeId.eq(followeeId)))
                        .fetchOne()
        );
    }
}