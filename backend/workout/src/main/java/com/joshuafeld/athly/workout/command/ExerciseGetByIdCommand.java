package com.joshuafeld.athly.workout.command;

/**
 * A command for getting an exercise by id.
 *
 * @param id the value for the {@code id} component
 * @param owner the value for the {@code owner} component
 */
public record ExerciseGetByIdCommand(Long id, Long owner) { }
