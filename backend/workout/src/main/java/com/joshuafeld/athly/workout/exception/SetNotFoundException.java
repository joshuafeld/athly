package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a set is not found.
 */
public class SetNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code SetNotFoundException} class.
     *
     * @param id the {@code id} of the set
     */
    public SetNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Set Not Found")
                        .detail("Set with id " + id + " not found")
                        .build(),
                null
        );
    }
}
