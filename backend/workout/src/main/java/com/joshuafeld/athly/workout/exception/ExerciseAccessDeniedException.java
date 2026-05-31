package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when an exercise is not accessible to the user.
 */
public class ExerciseAccessDeniedException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code ExerciseAccessDeniedException} class.
     *
     * @param id the {@code id} of the exercise
     */
    public ExerciseAccessDeniedException(final Long id) {
        super(
                HttpStatus.FORBIDDEN,
                new ProblemDetailBuilder(HttpStatus.FORBIDDEN)
                        .title("Access Denied")
                        .detail("Exercise with id " + id + " does not belong to you.")
                        .build(),
                null
        );
    }
}
