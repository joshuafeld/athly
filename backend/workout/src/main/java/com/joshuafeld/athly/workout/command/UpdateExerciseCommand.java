package com.joshuafeld.athly.workout.command;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * A command for updating an exercise.
 *
 * @param id the value for the {@code id} component
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 * @param userId the value for the {@code userId} component
 */
public record UpdateExerciseCommand(
        @NotNull @Positive Long id,
        @Size(max = 100) String name,
        Equipment equipment,
        Muscle muscle,
        @Positive Long userId
) { }
