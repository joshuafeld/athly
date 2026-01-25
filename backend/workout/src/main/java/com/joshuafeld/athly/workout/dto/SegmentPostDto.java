package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object for a segment post request.
 *
 * @param workout the value for the {@code workout} component
 * @param exercise the value for the {@code exercise} component
 * @param rest the value for the {@code rest} component
 */
public record SegmentPostDto(
        @NotNull Long workout,
        @NotNull Long exercise,
        Integer rest
) { }
