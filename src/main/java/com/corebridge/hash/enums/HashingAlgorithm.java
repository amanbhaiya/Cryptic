package com.corebridge.hash.enums;

import com.corebridge.core.contracts.Algorithm;

/**
 * An enum representing common hashing algorithms.
 */
public enum HashingAlgorithm implements Algorithm {
    /**
     * Message Digest 5. Note: This is a legacy algorithm and is not considered secure.
     */
    MD5("MD5"),
    /**
     * Secure Hash Algorithm 1. Note: This is a legacy algorithm and is not considered secure.
     */
    SHA_1("SHA-1"),
    /**
     * Secure Hash Algorithm 256-bit.
     */
    SHA_256("SHA-256"),
    /**
     * Secure Hash Algorithm 384-bit.
     */
    SHA_384("SHA-384"),
    /**
     * Secure Hash Algorithm 512-bit.
     */
    SHA_512("SHA-512");

    private final String algorithm;

    /**
     * Constructs a new HashingAlgorithm with the specified algorithm name.
     *
     * @param algorithm The name of the algorithm.
     */
    HashingAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}