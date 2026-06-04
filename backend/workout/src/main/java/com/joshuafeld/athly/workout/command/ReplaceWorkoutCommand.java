package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * A command for replacing a workout.
 *
 * @param id the value for the {@code id} component
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 * @param userId the value for the {@code userId} component
 */
public record ReplaceWorkoutCommand(
        @NotNull @Positive Long id,
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 1000) String notes,
        @NotNull @Positive Long userId
) { }
