package com.corebridge.core.exceptions;

/**
 * This exception is thrown when an error occurs during a hashing operation.
 */
public class HashingException extends RuntimeException {

    /**
     * Constructs a new HashingException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }
}