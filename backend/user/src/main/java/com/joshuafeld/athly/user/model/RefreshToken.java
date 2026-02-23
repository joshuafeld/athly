package com.joshuafeld.athly.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    private Instant expiresAt;

    private boolean revoked;

    /**
     * Creates an instance of a {@code RefreshToken} class.
     *
     * @param token the value for the {@code token} component
     * @param user the value for the {@code user} component
     * @param expiresAt the value for the {@code expiresAt} component
     */
    public RefreshToken(final String token,
                        final User user,
                        final Instant expiresAt) {
        this.token = token;
        this.user = user;
        this.expiresAt = expiresAt;
        this.revoked = false;
    }
}
