package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.Size;

/**
 * A data transfer object for a workout patch request.
 *
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 */
public record PatchWorkoutDto(
        @Size(max = 100) String name,
        @Size(max = 1000) String notes
) { }
