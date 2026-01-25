package com.joshuafeld.athly.workout.dto;

import jakarta.validation.constraints.NotNull;
/**
 * A data transfer object for a segment put request.
 *
 * @param exercise the value for the {@code exercise} component
 * @param rest the value for the {@code rest} component
 */
public record SegmentPutDto(@NotNull Long exercise, Integer rest) { }
