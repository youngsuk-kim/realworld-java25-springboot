package com.bread.realworld.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "follows", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "followee_id"})
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followerId;
    private Long followeeId;
    private LocalDateTime followedAt;

    protected Follow() {
    }

    private Follow(final Long followerId, final Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
        this.followedAt = LocalDateTime.now();
    }

    @Builder
    private Follow(final Long id, final Long followerId, final Long followeeId, final LocalDateTime followedAt) {
        this.id = id;
        this.followerId = followerId;
        this.followeeId = followeeId;
        this.followedAt = followedAt;
    }

    public static Follow create(final Long followerId, final Long followeeId) {
        validateNotSelfFollow(followerId, followeeId);
        return new Follow(followerId, followeeId);
    }

    private static void validateNotSelfFollow(final Long followerId, final Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }
    }
}
