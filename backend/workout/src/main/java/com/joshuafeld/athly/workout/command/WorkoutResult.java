package com.joshuafeld.athly.workout.command;

import java.util.List;

/**
 * A result for getting a workout.
 *
 * @param id the value for the {@code id} component
 * @param segmentIds the value for the {@code segmentIds} component
 * @param name the value for the {@code name} component
 * @param notes the value for the {@code notes} component
 */
public record WorkoutResult(Long id, List<Long> segmentIds, String name, String notes) { }
