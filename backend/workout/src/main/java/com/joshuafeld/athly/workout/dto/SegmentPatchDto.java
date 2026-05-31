package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.Min;

/**
 * A data transfer object for a segment patch request.
 *
 * @param exercise the value for the {@code exercise} component
 * @param rest the value for the {@code rest} component
 * @param notes the value for the {@code notes} component
 */
public record SegmentPatchDto(
        Long exercise,
        @Min(0) Integer rest,
        String notes
) { }
