package com.joshuafeld.athly.workout.command;

/**
 * A command for getting an exercise.
 *
 * @param id the value for the {@code id} component
 * @param owner the value for the {@code owner} component
 */
public record GetExerciseCommand(Long id, Long owner) { }
