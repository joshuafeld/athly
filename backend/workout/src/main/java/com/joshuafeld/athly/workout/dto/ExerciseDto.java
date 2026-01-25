package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.Equipment;
import com.joshuafeld.athly.workout.model.Muscle;

/**
 * A data transfer object for an exercise request response.
 *
 * @param id the value of the {@code id} component
 * @param name the value of the {@code name} component
 * @param equipment the value of the {@code equipment} component
 * @param muscle the value of the {@code muscle} component
 * @param creator the value of the {@code creator} component
 */
public record ExerciseDto(
        Long id,
        String name,
        Equipment equipment,
        Muscle muscle,
        Long creator
) { }
