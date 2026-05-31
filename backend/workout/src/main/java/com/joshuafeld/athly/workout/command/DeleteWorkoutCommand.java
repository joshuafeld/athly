package com.joshuafeld.athly.workout.command;

/**
 * A command for deleting a workout.
 *
 * @param id the value for the {@code id} component
 * @param owner the value for the {@code owner} component
 */
public record DeleteWorkoutCommand(Long id, Long owner) { }
