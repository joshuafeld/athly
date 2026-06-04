package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A command for getting many exercises.
 *
 * @param userId the value for the {@code userId} component
 */
public record GetManyExercisesCommand(@NotNull @Positive Long userId) { }
