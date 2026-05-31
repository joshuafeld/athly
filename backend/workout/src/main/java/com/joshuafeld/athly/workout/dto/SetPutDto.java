package com.joshuafeld.athly.workout.dto;

import com.joshuafeld.athly.workout.model.SetType;
import jakarta.validation.constraints.Min;
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
        @NotNull @Min(0) Double weight,
        @NotNull @Min(0) Integer reps,
        @NotNull Boolean done
) { }
