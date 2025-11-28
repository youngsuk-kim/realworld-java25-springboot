package com.bread.realworld.service;

import com.bread.realworld.domain.User;
import com.bread.realworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(final UserRegistration userRegistration) {
        validateEmailNotExists(userRegistration.email());
        validateUsernameNotExists(userRegistration.username());

        final String encodedPassword = passwordEncoder.encode(userRegistration.password());
        final User user = User.register(userRegistration.username(), userRegistration.email(), encodedPassword);

        return userRepository.save(user);
    }

    private void validateEmailNotExists(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }
    }

    private void validateUsernameNotExists(final String username) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }
}