package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.SetType;

/**
 * A data transfer object for a set patch request.
 *
 * @param type the value for the {@code type} component
 * @param weight the value for the {@code weight} component
 * @param reps the value for the {@code reps} component
 * @param done the value for the {@code done} component
 */
public record SetPatchDto(
        SetType type,
        Double weight,
        Integer reps,
        Boolean done
) { }
