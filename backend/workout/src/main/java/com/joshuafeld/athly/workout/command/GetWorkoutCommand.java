package com.joshuafeld.athly.workout.command;

/**
 * A command for getting a workout.
 *
 * @param id the value for the {@code id} component
 * @param owner the value for the {@code owner} component
 */
public record GetWorkoutCommand(Long id, Long owner) { }
