package com.joshuafeld.athly.nutrition.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a serving is not found.
 */
public class ServingNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code ServingNotFoundException} class.
     *
     * @param id the {@code id} of the serving
     */
    public ServingNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Serving Not Found")
                        .detail("Serving with id " + id + " not found")
                        .build(),
                null
        );
    }
}
