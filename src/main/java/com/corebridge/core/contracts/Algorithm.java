package com.corebridge.core.contracts;

/**
 * An interface for classes that represent a cryptographic algorithm.
 * This provides a common method to retrieve the name of the algorithm being used.
 */
public interface Algorithm {

    /**
     * Returns the standard name of the algorithm.
     *
     * @return The algorithm name.
     */
    String getAlgorithm();
}