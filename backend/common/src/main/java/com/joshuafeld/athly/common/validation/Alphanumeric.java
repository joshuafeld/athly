package com.joshuafeld.athly.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A constraint annotation for alphanumeric strings.
 */
@Documented
@Constraint(validatedBy = AlphanumericValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Alphanumeric {

    /**
     * Returns the value of the {@code message} component.
     *
     * @return the value of the {@code message} component
     */
    String message() default "must contain only letters and numbers";

    /**
     * Returns the value of the {@code groups} component.
     *
     * @return the value of the {@code groups} component
     */
    Class<?>[] groups() default {};

    /**
     * Returns the value of the {@code payload} component.
     *
     * @return the value of the {@code payload} component
     */
    Class<? extends Payload>[] payload() default {};
}
