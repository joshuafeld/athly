package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when an exercise is not found.
 */
public class ExerciseNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code ExerciseNotFoundException} class.
     *
     * @param id the {@code id} of the exercise
     */
    public ExerciseNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Exercise Not Found")
                        .detail("Exercise with id " + id + " not found")
                        .build(),
                null
        );
    }
}
