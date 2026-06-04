package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A command for getting a workout.
 *
 * @param id the value for the {@code id} component
 * @param userId the value for the {@code userId} component
 */
public record GetWorkoutCommand(
        @NotNull @Positive Long id,
        @NotNull @Positive Long userId
) { }
