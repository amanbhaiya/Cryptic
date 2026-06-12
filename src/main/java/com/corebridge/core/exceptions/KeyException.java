package com.corebridge.core.exceptions;

/**
 * This exception is thrown when an error occurs during a cryptographic key operation,
 * such as generation or conversion.
 */
public class KeyException extends RuntimeException {

    /**
     * Constructs a new KeyException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public KeyException(String message, Throwable cause) {
        super(message, cause);
    }
}