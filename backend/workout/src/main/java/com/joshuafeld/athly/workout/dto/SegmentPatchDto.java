package com.joshuafeld.athly.workout.dto;

/**
 * A data transfer object for a segment patch request.
 *
 * @param exercise the value for the {@code exercise} component
 * @param rest the value for the {@code rest} component
 */
public record SegmentPatchDto(Long exercise, Integer rest) { }
