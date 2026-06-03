package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A command for getting exercises.
 *
 * @param owner the value for the {@code owner} component
 */
public record GetAllExercisesCommand(@NotNull @Positive Long owner) { }
