package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;
import jakarta.validation.constraints.Size;

/**
 * A data transfer object for an exercise patch request.
 *
 * @param name the value for the {@code name} component
 * @param equipment the value for the {@code equipment} component
 * @param muscle the value for the {@code muscle} component
 */
public record PatchExerciseDto(
        @Size(max = 100) String name,
        Equipment equipment,
        Muscle muscle
) { }
