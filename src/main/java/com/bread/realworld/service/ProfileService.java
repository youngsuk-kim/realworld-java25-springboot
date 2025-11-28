package com.bread.realworld.service;

import com.bread.realworld.domain.Follow;
import com.bread.realworld.domain.User;
import com.bread.realworld.repository.FollowRepository;
import com.bread.realworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public ProfileResult getProfile(final String username, final Long currentUserId) {
        final User user = findUserByUsername(username);
        final boolean following = isFollowing(currentUserId, user.getId());
        return new ProfileResult(user, following);
    }

    @Transactional
    public ProfileResult follow(final String username, final Long currentUserId) {
        final User user = findUserByUsername(username);

        if (!followRepository.existsByFollowerIdAndFolloweeId(currentUserId, user.getId())) {
            final Follow follow = Follow.create(currentUserId, user.getId());
            followRepository.save(follow);
        }

        return new ProfileResult(user, true);
    }

    @Transactional
    public ProfileResult unfollow(final String username, final Long currentUserId) {
        final User user = findUserByUsername(username);

        followRepository.findByFollowerIdAndFolloweeId(currentUserId, user.getId())
                .ifPresent(followRepository::delete);

        return new ProfileResult(user, false);
    }

    private User findUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    private boolean isFollowing(final Long followerId, final Long followeeId) {
        if (followerId == null) return false;
        return followRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId);
    }
}