package com.joshuafeld.athly.workout.command;

/**
 * A command for getting exercises.
 *
 * @param owner the value for the {@code owner} component
 */
public record GetExercisesCommand(Long owner) { }
