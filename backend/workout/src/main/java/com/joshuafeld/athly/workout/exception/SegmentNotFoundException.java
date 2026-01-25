package com.joshuafeld.athly.workout.exception;

import com.joshuafeld.athly.common.exception.ProblemDetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

/**
 * An exception that is thrown when a segment is not found.
 */
public class SegmentNotFoundException extends ErrorResponseException {

    /**
     * Creates an instance of a {@code SegmentNotFoundException} class.
     *
     * @param id the {@code id} of the segment
     */
    public SegmentNotFoundException(final Long id) {
        super(
                HttpStatus.NOT_FOUND,
                new ProblemDetailBuilder(HttpStatus.NOT_FOUND)
                        .title("Segment Not Found")
                        .detail("Segment with id " + id + " not found")
                        .build(),
                null
        );
    }
}
