package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for an exercise post request.
 *
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 * @param creator the value for the {@code creator} component
 */
public record ExercisePostDto(
        @NotBlank String name,
        @NotNull Equipment equipment,
        @NotNull Muscle muscle,
        Long creator
) { }
