package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A command for deleting a workout.
 *
 * @param id the value for the {@code id} component
 * @param owner the value for the {@code owner} component
 */
public record DeleteWorkoutCommand(
        @NotNull @Positive Long id,
        @NotNull @Positive Long owner
) { }
