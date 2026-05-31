package com.joshuafeld.athly.workout.command;

/**
 * A command for getting all exercises.
 *
 * @param owner the value for the {@code owner} component
 */
public record ExerciseGetAllCommand(Long owner) { }
