package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.NutrientType;

/**
 * A data transfer object for a nutrient patch request.
 *
 * @param type the value for the {@code type} component
 * @param value the value for the {@code value} component
 */
public record NutrientPatchDto(
        NutrientType type,
        Double value
) { }
