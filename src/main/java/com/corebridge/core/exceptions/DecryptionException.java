package com.corebridge.core.exceptions;

/**
 * This exception is thrown when an error occurs during a decryption operation.
 */
public class DecryptionException extends RuntimeException {

    /**
     * Constructs a new DecryptionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public DecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}