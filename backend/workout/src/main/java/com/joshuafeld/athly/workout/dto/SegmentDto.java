package com.joshuafeld.athly.workout.dto;

import java.util.List;

/**
 * A data transfer object for a segment request response.
 *
 * @param id the value of the {@code id} component
 * @param workout the value of the {@code workout} component
 * @param exercise the value of the {@code exercise} component
 * @param sets the value of the {@code sets} component
 * @param rest the value of the {@code rest} component
 */
public record SegmentDto(
        Long id,
        Long workout,
        Long exercise,
        List<Long> sets,
        Integer rest
) { }
