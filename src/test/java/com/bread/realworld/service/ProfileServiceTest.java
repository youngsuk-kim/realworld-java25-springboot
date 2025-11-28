package com.bread.realworld.service;

import com.bread.realworld.domain.Follow;
import com.bread.realworld.repository.FollowRepository;
import com.bread.realworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.bread.realworld.fixtures.Fixtures.aFollow;
import static com.bread.realworld.fixtures.Fixtures.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FollowRepository followRepository;

    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileService = new ProfileService(userRepository, followRepository);
    }

    @Test
    void when_getProfile_without_login_expect_following_is_false() {
        var user = aUser().build();

        given(userRepository.findByUsername(user.getUserName())).willReturn(Optional.of(user));

        var result = profileService.getProfile(user.getUserName(), null);

        assertThat(result.following()).isFalse();
        assertThat(result.username()).isEqualTo(user.getUserName());
    }

    @Test
    void when_getProfile_with_following_user_expect_following_is_true() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.existsByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(true);

        var result = profileService.getProfile(followee.getUserName(), follower.getId());

        assertThat(result.following()).isTrue();
    }

    @Test
    void when_getProfile_with_not_following_user_expect_following_is_false() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.existsByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(false);

        var result = profileService.getProfile(followee.getUserName(), follower.getId());

        assertThat(result.following()).isFalse();
    }

    @Test
    void when_getProfile_with_nonexistent_user_expect_exception_thrown() {
        var nonexistentUsername = "nonexistent";

        given(userRepository.findByUsername(nonexistentUsername)).willReturn(Optional.empty());

        assertThatThrownBy(() -> profileService.getProfile(nonexistentUsername, 1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(nonexistentUsername);
    }

    @Test
    void when_follow_expect_follow_created() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.existsByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(false);

        var result = profileService.follow(followee.getUserName(), follower.getId());

        assertThat(result.following()).isTrue();
        verify(followRepository).save(any(Follow.class));
    }

    @Test
    void when_follow_already_following_user_expect_follow_not_duplicated() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.existsByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(true);

        var result = profileService.follow(followee.getUserName(), follower.getId());

        assertThat(result.following()).isTrue();
        verify(followRepository, never()).save(any(Follow.class));
    }

    @Test
    void when_unfollow_expect_follow_deleted() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();
        var follow = aFollow().followerId(follower.getId()).followeeId(followee.getId()).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.findByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(Optional.of(follow));

        var result = profileService.unfollow(followee.getUserName(), follower.getId());

        assertThat(result.following()).isFalse();
        verify(followRepository).delete(follow);
    }

    @Test
    void when_unfollow_not_following_user_expect_no_error() {
        var follower = aUser().id(1L).build();
        var followee = aUser().id(2L).build();

        given(userRepository.findByUsername(followee.getUserName())).willReturn(Optional.of(followee));
        given(followRepository.findByFollowerIdAndFolloweeId(follower.getId(), followee.getId())).willReturn(Optional.empty());

        var result = profileService.unfollow(followee.getUserName(), follower.getId());

        assertThat(result.following()).isFalse();
        verify(followRepository, never()).delete(any(Follow.class));
    }
}