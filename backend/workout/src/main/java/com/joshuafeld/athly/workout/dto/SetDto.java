package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.SetType;

/**
 * A data transfer object for a set request response.
 *
 * @param id the value of the {@code id} component
 * @param segment the value of the {@code segment} component
 * @param type the value of the {@code type} component
 * @param weight the value of the {@code weight} component
 * @param reps the value of the {@code reps} component
 * @param done the value of the {@code done} component
 */
public record SetDto(
        Long id,
        Long segment,
        SetType type,
        Double weight,
        Integer reps,
        Boolean done
) { }
