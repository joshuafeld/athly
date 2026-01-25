package com.joshuafeld.athly.nutrition.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * A data transfer object for a food put request.
 *
 * @param name the value for the {@code name} component
 * @param manufacturer the value for the {@code manufacturer} component
 * @param creator the value for the {@code creator} component
 */
public record FoodPutDto(
        @NotBlank String name,
        @NotBlank String manufacturer,
        Long creator
) { }
