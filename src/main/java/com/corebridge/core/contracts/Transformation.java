package com.corebridge.core.contracts;

/**
 * An interface for cryptographic transformations, which include an algorithm,
 * a mode of operation, and a padding scheme.
 */
public interface Transformation extends Algorithm {

    /**
     * Returns the mode of operation for the algorithm (e.g., ECB, GCM).
     *
     * @return The mode of operation.
     */
    String getMode();

    /**
     * Returns the padding scheme used for the algorithm (e.g., NoPadding, PKCS5Padding).
     *
     * @return The padding scheme.
     */
    String getPaddingScheme();

    /**
     * Returns the full transformation string in the format "algorithm/mode/padding".
     *
     * @return The full transformation string.
     */
    default String getTransformation() {
        return String.join("/", getAlgorithm(), getMode(), getPaddingScheme());
    }
}