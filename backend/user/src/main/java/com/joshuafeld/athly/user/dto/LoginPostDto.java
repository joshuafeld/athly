package com.joshuafeld.athly.user.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * A data transfer object for a login post request.
 *
 * @param username the value for the {@code username} component
 * @param password the value for the {@code password} component
 */
public record LoginPostDto(@NotBlank String username,
                           @NotBlank String password) { }
