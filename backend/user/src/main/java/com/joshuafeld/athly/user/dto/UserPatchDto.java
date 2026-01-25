package com.joshuafeld.athly.user.dto;

import com.joshuafeld.athly.common.validation.Alphanumeric;
import com.joshuafeld.athly.common.validation.Lowercase;
import jakarta.validation.constraints.Email;

/**
 * A data transfer object for a user patch request.
 *
 * @param username the value for the {@code username} component
 * @param email the value for the {@code email} component
 * @param firstName the value for the {@code firstName} component
 * @param lastName the value for the {@code lastName} component
 */
public record UserPatchDto(
        @Alphanumeric @Lowercase String username,
        @Email String email,
        String firstName,
        String lastName
) { }
