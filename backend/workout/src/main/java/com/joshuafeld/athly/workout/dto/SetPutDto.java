package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.SetType;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a set put request.
 *
 * @param type the value for the {@code type} component
 * @param weight the value for the {@code weight} component
 * @param reps the value for the {@code reps} component
 * @param done the value for the {@code done} component
 */
public record SetPutDto(
        @NotNull SetType type,
        @NotNull Double weight,
        @NotNull Integer reps,
        @NotNull Boolean done
) { }
