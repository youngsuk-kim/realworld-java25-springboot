package com.bread.realworld.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Bio {
    private String bio;

    public Bio(final String bio) {
        this.bio = bio;
    }

    protected Bio() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bio bio1 = (Bio) o;
        return Objects.equals(bio, bio1.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bio);
    }
}