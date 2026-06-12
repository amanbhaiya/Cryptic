package com.corebridge.core.exceptions;

/**
 * This exception is thrown when an error occurs during a digital signature operation,
 * such as signing or verification.
 */
public class SignatureException extends RuntimeException {

    /**
     * Constructs a new SignatureException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}