package com.bread.realworld.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Bio bio;

    @Embedded
    private Image image;

    protected User() {
    }

    private User(final Username username, final Email email, final Password password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Builder
    private User(final Long id, final Username username, final Email email, final Password password,
                 final Bio bio, final Image image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public static User register(final String username, final String email, final String password) {
        return new User(new Username(username), new Email(email), new Password(password));
    }

    public String getUserName() {
        return username.getUsername();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getEncodedPassword() {
        return password.getEncodedPassword();
    }

    public String getBio() {
        return bio != null ? bio.getBio() : null;
    }

    public String getImage() {
        return image != null ? image.getImage() : null;
    }
}