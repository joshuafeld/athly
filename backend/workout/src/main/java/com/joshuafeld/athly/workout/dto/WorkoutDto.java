package com.joshuafeld.athly.workout.dto;

import java.util.List;

/**
 * A data transfer object for a workout request response.
 *
 * @param id the value of the {@code id} component
 * @param segments the value of the {@code segments} component
 * @param name the value of the {@code name} component
 * @param notes the value of the {@code notes} component
 */
public record WorkoutDto(
        Long id,
        List<Long> segments,
        String name,
        String notes
) { }
