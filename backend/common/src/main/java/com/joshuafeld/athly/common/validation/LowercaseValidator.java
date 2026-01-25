package com.joshuafeld.athly.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * A validator for lowercase strings.
 */
public class LowercaseValidator
        implements ConstraintValidator<Lowercase, String> {

    /**
     * Implements the lowercase validation logic.
     *
     * @param value the object to validator
     * @param context the context in which the constraint is evaluated
     * @return {@code false} if the value does not pass the constraint
     */
    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return value.equals(value.toLowerCase());
    }
}
