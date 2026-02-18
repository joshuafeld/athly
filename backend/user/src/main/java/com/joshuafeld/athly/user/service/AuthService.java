package com.joshuafeld.athly.user.service;

import com.joshuafeld.athly.user.security.JwtProperties;
import com.joshuafeld.athly.user.dto.*;
import com.joshuafeld.athly.user.mapper.UserMapper;
import com.joshuafeld.athly.user.model.User;
import com.joshuafeld.athly.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * An authentication service.
 */
@Service
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProperties;

    /**
     * Creates an instance of a {@code UserService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param mapper the value for the {@code mapper} component
     * @param passwordEncoder the value for the {@code passwordEncoder} component
     * @param jwtEncoder the value for the {@code jwtEncoder} component
     * @param jwtProperties the value for the {@code jwtProperties} component
     */
    public AuthService(final UserRepository repository,
                       final UserMapper mapper,
                       final PasswordEncoder passwordEncoder,
                       final JwtEncoder jwtEncoder,
                       final JwtProperties jwtProperties) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.jwtProperties = jwtProperties;
    }

    /**
     * Logs a user in.
     *
     * @param dto the data for the login
     * @return the data of the token
     */
    public LoginDto login(final LoginPostDto dto) {
        final String username = dto.username();
        final User user = repository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Unknown user"));
        if (!passwordEncoder.matches(dto.password(), user.password())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new LoginDto(jwtEncoder.encode(JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                        .subject(String.valueOf(user.id()))
                        .expiresAt(Instant.now().plusSeconds(jwtProperties.ttl()))
                        .build()
        )).getTokenValue(), "Bearer", jwtProperties.ttl());
    }

    /**
     * Registers a new user.
     *
     * @param dto the data for the registration
     * @return the data of the user
     */
    public UserDto register(final RegisterPostDto dto) {
        repository.findByUsername(dto.username()).ifPresent(user -> {
            throw new IllegalArgumentException("Username taken");
        });
        return mapper.toDto(repository.save(new User(
                dto.username(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                dto.firstName(),
                dto.lastName()
        )));
    }
}
