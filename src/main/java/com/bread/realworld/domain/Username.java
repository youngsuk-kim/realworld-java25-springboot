package com.bread.realworld.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Username {
    private String username;

    public Username(final String username) {
        this.username = username;
    }

    public Username() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
