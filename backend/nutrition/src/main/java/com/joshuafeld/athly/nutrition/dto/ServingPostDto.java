package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.ServingType;
import com.joshuafeld.athly.nutrition.model.Unit;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a serving post request.
 *
 * @param food the value for the {@code food} component
 * @param type the value for the {@code type} component
 * @param value the value for the {@code value} component
 * @param unit the value for the {@code unit} component
 */
public record ServingPostDto(
        @NotNull Long food,
        @NotNull ServingType type,
        @NotNull Double value,
        @NotNull Unit unit
) { }
