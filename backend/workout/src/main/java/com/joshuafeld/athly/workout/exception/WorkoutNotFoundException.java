package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a workout is not found.
 */
public class WorkoutNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code WorkoutNotFoundException} class.
     *
     * @param id the {@code id} of the workout
     */
    public WorkoutNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Workout Not Found")
                        .detail("Workout with id " + id + " not found")
                        .build(),
                null
        );
    }
}
