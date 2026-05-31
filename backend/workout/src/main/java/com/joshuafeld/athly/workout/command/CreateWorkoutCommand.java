package com.joshuafeld.athly.workout.command;

/**
 * A command for creating a workout.
 *
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 * @param owner the value for the {@code owner} component
 */
public record CreateWorkoutCommand(String name, String notes, Long owner) { }
