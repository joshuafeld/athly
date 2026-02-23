package com.joshuafeld.athly.user.service;

import com.joshuafeld.athly.user.model.RefreshToken;
import com.joshuafeld.athly.user.model.Role;
import com.joshuafeld.athly.user.repository.RefreshTokenRepository;
import com.joshuafeld.athly.user.security.JwtProperties;
import com.joshuafeld.athly.user.dto.*;
import com.joshuafeld.athly.user.mapper.UserMapper;
import com.joshuafeld.athly.user.model.User;
import com.joshuafeld.athly.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * An authentication service.
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProperties;

    /**
     * Logs a user in.
     *
     * @param dto the data for the login
     * @return the data of the token
     */
    @Transactional
    public LoginDto login(final LoginPostDto dto) {
        final User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new BadCredentialsException("Unknown user"));
        if (!passwordEncoder.matches(dto.password(), user.password())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new LoginDto(generateToken(user), generateRefreshToken(user),
                jwtProperties.ttl());
    }

    /**
     * Registers a new user.
     *
     * @param dto the data for the registration
     * @return the data of the user
     */
    @Transactional
    public UserDto register(final RegisterPostDto dto) {
        userRepository.findByUsername(dto.username()).ifPresent(user -> {
            throw new IllegalArgumentException("Username taken");
        });
        return mapper.toDto(userRepository.save(new User(
                dto.username(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                Set.of(Role.USER),
                dto.firstName(),
                dto.lastName()
        )));
    }

    /**
     * Refreshes an access token.
     *
     * @param dto the data for the refresh
     * @return the data of the token
     */
    @Transactional
    public RefreshDto refresh(final RefreshPostDto dto) {
        final RefreshToken token = refreshTokenRepository
                .findByToken(dto.refreshToken())
                .orElseThrow(() -> new BadCredentialsException("Invalid token"));
        if (token.revoked()) {
            throw new BadCredentialsException("Token revoked");
        }
        if (token.expiresAt().isBefore(Instant.now())) {
            throw new BadCredentialsException("Token expired");
        }
        token.revoked(true);
        final User user = token.user();
        return new RefreshDto(generateToken(user), generateRefreshToken(user),
                jwtProperties.ttl());
    }

    /**
     * Logs a user out.
     *
     * @param dto the data for the logout
     */
    @Transactional
    public void logout(final LogoutPostDto dto) {
        refreshTokenRepository.findByToken(dto.refreshToken())
                .ifPresentOrElse(token -> token.revoked(true), () -> {
                    throw new BadCredentialsException("Invalid token");
                });
    }

    private String generateToken(final User user) {
        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                        .subject(String.valueOf(user.id()))
                        .expiresAt(Instant.now().plusSeconds(jwtProperties.ttl()))
                        .claim("roles", user.roles().stream().map(Role::name)
                                .collect(Collectors.toSet()))
                        .build()
        )).getTokenValue();
    }

    private String generateRefreshToken(final User user) {
        final String token = UUID.randomUUID().toString();
        refreshTokenRepository.save(new RefreshToken(token, user,
                Instant.now().plus(7, ChronoUnit.DAYS)));
        return token;
    }
}
