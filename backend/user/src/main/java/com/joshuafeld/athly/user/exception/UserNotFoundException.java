package com.joshuafeld.athly.user.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a user is not found.
 */
public class UserNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code UserNotFoundException} class.
     *
     * @param id the {@code id} of the user
     */
    public UserNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("User Not Found")
                        .detail("User with id " + id + " not found")
                        .build(),
                null
        );
    }
}
