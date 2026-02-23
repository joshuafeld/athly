package com.joshuafeld.athly.user.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * A data transfer object for a refresh post request.
 *
 * @param refreshToken the value for the {@code refreshToken} component
 */
public record RefreshPostDto(@NotBlank String refreshToken) { }
