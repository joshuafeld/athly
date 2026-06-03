package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * A command for updating a workout.
 *
 * @param id the value for the {@code id} component
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 * @param owner the value for the {@code owner} component
 */
public record UpdateWorkoutCommand(
        @NotNull @Positive Long id,
        @Size(max = 100) String name,
        @Size(max = 1000) String notes,
        @NotNull @Positive Long owner
) { }
