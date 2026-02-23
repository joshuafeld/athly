package com.joshuafeld.athly.user.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * A data transfer object for a logout post request.
 */
public record LogoutPostDto(@NotBlank String refreshToken) { }
