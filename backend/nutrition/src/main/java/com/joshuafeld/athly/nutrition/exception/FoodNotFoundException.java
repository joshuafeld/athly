package com.joshuafeld.athly.nutrition.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a food is not found.
 */
public class FoodNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code FoodNotFoundException} class.
     *
     * @param id the {@code id} of the food
     */
    public FoodNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Food Not Found")
                        .detail("Food with id " + id + " not found")
                        .build(),
                null
        );
    }
}
