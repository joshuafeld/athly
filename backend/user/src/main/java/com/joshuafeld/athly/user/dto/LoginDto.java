package com.joshuafeld.athly.user.dto;

/**
 * A data transfer object for a login request response.
 *
 * @param token the value of the {@code token} component
 * @param type the value of the {@code type} component
 * @param expiresAt the value of the {@code expiresAt} component
 */
public record LoginDto(String token, String type, Long expiresAt) { }
