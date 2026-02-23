package com.joshuafeld.athly.user.repository;

import com.joshuafeld.athly.user.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * A refresh token repository.
 */
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    /**
     * Retrieves a user by its username.
     *
     * @param token must not be {@code null}
     * @return the refresh token with the given token or
     *         {@code Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code token} is {@code null}
     */
    Optional<RefreshToken> findByToken(final String token);
}
