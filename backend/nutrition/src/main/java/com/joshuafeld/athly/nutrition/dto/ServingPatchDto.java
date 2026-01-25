package com.joshuafeld.athly.nutrition.dto;

import com.joshuafeld.athly.nutrition.model.ServingType;
import com.joshuafeld.athly.nutrition.model.Unit;

/**
 * A data transfer object for a serving patch request.
 *
 * @param type the value of the {@code type} component
 * @param value the value of the {@code value} component
 * @param unit the value of the {@code unit} component
 */
public record ServingPatchDto(
        ServingType type,
        Double value,
        Unit unit
) { }
