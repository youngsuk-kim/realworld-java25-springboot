package com.bread.realworld.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Password {
    private String encodedPassword;

    public Password(final String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    protected Password() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(encodedPassword, password.encodedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(encodedPassword);
    }
}
