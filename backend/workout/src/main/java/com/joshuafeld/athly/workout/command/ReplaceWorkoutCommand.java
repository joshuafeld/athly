package com.joshuafeld.athly.workout.command;

/**
 * A command for replacing a workout.
 *
 * @param id the value for the {@code id} component
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 * @param owner the value for the {@code owner} component
 */
public record ReplaceWorkoutCommand(
        Long id,
        String name,
        String notes,
        Long owner
) { }
