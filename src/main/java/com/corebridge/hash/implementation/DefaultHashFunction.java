package com.corebridge.hash.implementation;

import com.corebridge.hash.contract.HashFunction;
import com.corebridge.hash.enums.HashingAlgorithm;

/**
 * A default implementation of the {@link HashFunction} interface.
 * This class uses the algorithm specified in its constructor to perform hashing operations.
 */
public class DefaultHashFunction implements HashFunction {
    private final HashingAlgorithm algorithm;

    /**
     * Constructs a new DefaultHashFunction with the specified hashing algorithm.
     *
     * @param algorithm The hashing algorithm to use.
     */
    public DefaultHashFunction(HashingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Returns the standard name of the hashing algorithm.
     *
     * @return The algorithm name.
     */
    @Override
    public String getAlgorithm() {
        return this.algorithm.getAlgorithm();
    }
}