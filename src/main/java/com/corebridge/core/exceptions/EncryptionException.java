package com.corebridge.core.exceptions;

/**
 * This exception is thrown when an error occurs during an encryption operation.
 */
public class EncryptionException extends RuntimeException {

    /**
     * Constructs a new EncryptionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public EncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}