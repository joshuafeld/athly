package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a workout post request.
 *
 * @param creator the value of the {@code creator} component
 */
public record WorkoutPostDto(@NotNull Long creator) { }
