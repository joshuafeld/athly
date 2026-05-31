package com.joshuafeld.athly.workout.dto;

/**
 * A data transfer object for a workout post request.
 *
 * @param name the value of the {@code name} component
 * @param notes the value of the {@code notes} component
 */
public record WorkoutPostDto(
        String name,
        String notes
) { }
