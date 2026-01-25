package com.joshuafeld.athly.user.dto;

/**
 * A data transfer object for a user request response.
 *
 * @param id the value of the {@code id} component
 * @param username the value of the {@code username} component
 * @param email the value of the {@code email} component
 * @param firstName the value of the {@code firstName} component
 * @param lastName the value of the {@code lastName} component
 */
public record UserDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName
) { }
