package com.joshuafeld.athly.workout.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A command for getting many workouts.
 *
 * @param userId the value for the {@code userId} component
 */
public record GetManyWorkoutsCommand(@NotNull @Positive Long userId) { }
