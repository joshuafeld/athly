package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * A data transfer object for a workout post request.
 *
 * @param name the value of the {@code name} component
 * @param notes the value of the {@code notes} component
 */
public record PostWorkoutDto(
        @NotBlank @Size(max = 100) String name,
        @Size(max = 1000) String notes
) { }
