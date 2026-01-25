package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.NutrientType;

/**
 * A data transfer object for a nutrient request response.
 *
 * @param id the value of the {@code id} component
 * @param food the value of the {@code food} component
 * @param type the value of the {@code type} component
 * @param value the value of the {@code value} component
 */
public record NutrientDto(
        Long id,
        Long food,
        NutrientType type,
        Double value
) { }
