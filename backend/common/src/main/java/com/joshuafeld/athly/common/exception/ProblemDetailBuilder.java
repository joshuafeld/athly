package com.joshuafeld.athly.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

/**
 * A builder for a {@link ProblemDetail} class.
 */
public class ProblemDetailBuilder {

    private final ProblemDetail problemDetail;

    /**
     * Creates an instance of a {@code ProblemDetailBuilder} class.
     *
     * @param status the value for the {@code status} component
     */
    public ProblemDetailBuilder(final HttpStatus status) {
        problemDetail = ProblemDetail.forStatus(status);
    }

    /**
     * Sets the value of the {@code detail} component.
     *
     * @param detail the value for the {@code detail} component
     * @return {@code this}
     */
    public ProblemDetailBuilder detail(final String detail) {
        problemDetail.setDetail(detail);
        return this;
    }

    /**
     * Sets the value of the {@code title} component.
     *
     * @param title the value for the {@code title} component
     * @return {@code this}
     */
    public ProblemDetailBuilder title(final String title) {
        problemDetail.setTitle(title);
        return this;
    }

    /**
     * Sets the value of the {@code type} component.
     *
     * @param type the value for the {@code type} component
     * @return {@code this}
     */
    public ProblemDetailBuilder type(final URI type) {
        problemDetail.setType(type);
        return this;
    }

    /**
     * Sets the value of the {@code instance} component.
     *
     * @param instance the value for the {@code instance} component
     * @return {@code this}
     */
    public ProblemDetailBuilder instance(final URI instance) {
        problemDetail.setInstance(instance);
        return this;
    }

    /**
     * Sets the value of a {@code property} component.
     *
     * @param name the name for the {@code property} component
     * @param value the value for the {@code property} component
     * @return {@code this}
     */
    public ProblemDetailBuilder property(final String name,
                                         final Object value) {
        problemDetail.setProperty(name, value);
        return this;
    }

    /**
     * Returns the {@code ProblemDetail} instance.
     *
     * @return the {@code ProblemDetail} instance
     */
    public ProblemDetail build() {
        return problemDetail;
    }
}
