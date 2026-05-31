package com.joshuafeld.athly.workout.dto;

/**
 * A data transfer object for a workout put request.
 *
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 */
public record WorkoutPutDto(String name, String notes) { }
