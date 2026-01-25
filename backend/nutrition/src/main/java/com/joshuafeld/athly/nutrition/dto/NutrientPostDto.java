package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.NutrientType;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a nutrient post request.
 *
 * @param food the value for the {@code food} component
 * @param type the value for the {@code type} component
 * @param value the value for the {@code value} component
 */
public record NutrientPostDto(
        @NotNull Long food,
        @NotNull NutrientType type,
        @NotNull Double value
) { }
