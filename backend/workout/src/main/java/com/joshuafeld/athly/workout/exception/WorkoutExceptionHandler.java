package com.joshuafeld.athly.workout.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * An exception handler for the workout service.
 */
@RestControllerAdvice
public class WorkoutExceptionHandler extends ResponseEntityExceptionHandler { }
