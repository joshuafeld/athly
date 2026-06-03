package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * A data transfer object for an exercise put request.
 *
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 */
public record PutExerciseDto(
        @NotBlank @Size(max = 100) String name,
        @NotNull Equipment equipment,
        @NotNull Muscle muscle
) { }
