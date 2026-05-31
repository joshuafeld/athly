package com.joshuafeld.athly.workout.command;

/**
 * A command for getting workouts.
 *
 * @param owner the value for the {@code owner} component
 */
public record GetWorkoutsCommand(Long owner) { }
