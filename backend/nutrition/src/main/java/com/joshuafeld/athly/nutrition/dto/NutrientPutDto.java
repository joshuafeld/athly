package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.NutrientType;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a nutrient put request.
 *
 * @param type the value for the {@code type} component
 * @param value the value for the {@code value} component
 */
public record NutrientPutDto(
        @NotNull NutrientType type,
        @NotNull Double value
) { }
