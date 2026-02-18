package com.joshuafeld.athly.user.dto;

import com.joshuafeld.athly.common.validation.Alphanumeric;
import com.joshuafeld.athly.common.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * A data transfer object for a registration post request.
 *
 * @param username the value for the {@code username} component
 * @param email the value for the {@code email} component
 * @param password the value for the {@code password} component
 * @param firstName the value for the {@code firstName} component
 * @param lastName the value for the {@code lastName} component
 */
public record RegisterPostDto(
        @Alphanumeric @Lowercase @NotBlank String username,
        @Email @NotBlank String email,
        @NotBlank String password,
        String firstName,
        String lastName
) { }
