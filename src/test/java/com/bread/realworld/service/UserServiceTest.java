package com.bread.realworld.service;

import com.bread.realworld.domain.User;
import com.bread.realworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.bread.realworld.fixtures.Fixtures.aRegisterUser;
import static com.bread.realworld.fixtures.Fixtures.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void when_register_expect_user_created_with_correct_information() {
        var registerUser = aRegisterUser();
        var savedUser = aUser().build();

        given(userRepository.save(any(User.class))).willReturn(savedUser);

        var user = userService.register(registerUser);

        assertThat(user).isEqualTo(savedUser);
    }

    @Test
    void when_register_expect_password_encoded() {
        var registerUser = aRegisterUser();

        given(userRepository.save(any(User.class))).willReturn(aUser().build());

        userService.register(registerUser);

        verify(passwordEncoder).encode(registerUser.password());
    }

    @Test
    void when_register_with_duplicate_email_expect_exception_thrown() {
        var registerUser = aRegisterUser();

        given(userRepository.existsByEmail(registerUser.email())).willReturn(true);

        assertThatThrownBy(() -> userService.register(registerUser))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void when_register_with_duplicate_username_expect_exception_thrown() {
        var registerUser = aRegisterUser();

        given(userRepository.existsByEmail(registerUser.email())).willReturn(false);
        given(userRepository.existsByUsername(registerUser.username())).willReturn(true);

        assertThatThrownBy(() -> userService.register(registerUser))
                .isInstanceOf(DuplicateUsernameException.class);
    }
}


