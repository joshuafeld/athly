package com.joshuafeld.athly.nutrition.dto;

import java.util.List;

/**
 * A data transfer object for a food request response.
 *
 * @param name the value for the {@code name} component
 * @param manufacturer the value for the {@code manufacturer} component
 * @param nutrients the value for the {@code nutrients} component
 * @param servings the value for the {@code servings} component
 * @param creator the value for the {@code creator} component
 */
public record FoodDto(
        Long id,
        String name,
        String manufacturer,
        List<Long> nutrients,
        List<Long> servings,
        Long creator
) { }
