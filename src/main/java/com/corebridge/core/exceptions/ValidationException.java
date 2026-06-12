package com.corebridge.core.exceptions;

/**
 * This exception is thrown when a validation check fails.
 * It is a runtime exception used to indicate that an argument or state is not valid.
 */
public class ValidationException extends RuntimeException {
    private final String validationMessage;

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ValidationException(String message) {
        this.validationMessage = message;
    }

    /**
     * Returns the detail message string of this validation exception.
     *
     * @return The detail message string.
     */
    public String getValidationMessage() {
        return validationMessage;
    }
}