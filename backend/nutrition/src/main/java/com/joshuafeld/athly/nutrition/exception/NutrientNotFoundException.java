package com.joshuafeld.athly.nutrition.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a nutrient is not found.
 */
public class NutrientNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code NutrientNotFoundException} class.
     *
     * @param id the {@code id} of the nutrient
     */
    public NutrientNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Nutrient Not Found")
                        .detail("Nutrient with id " + id + " not found")
                        .build(),
                null
        );
    }
}
