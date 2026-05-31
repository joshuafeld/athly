package com.joshuafeld.athly.workout.dto;

/**
 * A data transfer object for a workout patch request.
 *
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 */
public record WorkoutPatchDto(String name, String notes) { }
