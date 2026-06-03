package com.joshuafeld.athly.workout.command;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * A command for creating an exercise.
 *
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 * @param owner the value for the {@code owner} component
 */
public record CreateExerciseCommand(
        @NotBlank @Size(max = 100) String name,
        @NotNull Equipment equipment,
        @NotNull Muscle muscle,
        @NotNull @Positive Long owner
) { }
