package com.bread.realworld.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Image {
    private String image;

    public Image(final String image) {
        this.image = image;
    }

    protected Image() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return Objects.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(image);
    }
}