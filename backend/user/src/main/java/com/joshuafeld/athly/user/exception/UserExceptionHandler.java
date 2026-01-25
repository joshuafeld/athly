package com.joshuafeld.athly.user.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * An exception handler for the user service.
 */
@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler { }
