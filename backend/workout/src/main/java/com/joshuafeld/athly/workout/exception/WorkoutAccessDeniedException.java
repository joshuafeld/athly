package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a workout is not accessible to the user.
 */
public class WorkoutAccessDeniedException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code WorkoutAccessDeniedException} class.
     *
     * @param id the {@code id} of the workout
     */
    public WorkoutAccessDeniedException(final Long id) {
        super(
                HttpStatus.FORBIDDEN,
                new ProblemDetailBuilder(HttpStatus.FORBIDDEN)
                        .title("Access Denied")
                        .detail("Workout with id " + id + " does not belong to you.")
                        .build(),
                null
        );
    }
}
