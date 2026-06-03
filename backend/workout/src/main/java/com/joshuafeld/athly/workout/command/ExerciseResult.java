package com.joshuafeld.athly.workout.command;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;

/**
 * A result for getting an exercise.
 *
 * @param id the value for the {@code id} component
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 */
public record ExerciseResult(Long id, String name, Equipment equipment, Muscle muscle) { }
