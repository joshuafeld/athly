package com.joshuafeld.athly.user.dto;

/**
 * A data transfer object for a refresh request response.
 *
 * @param token the value of the {@code token} component
 * @param refreshToken the value of the {@code refreshToken} component
 * @param ttl the value of the {@code ttl} component
 */
public record RefreshDto(String token, String refreshToken, Long ttl) { }
