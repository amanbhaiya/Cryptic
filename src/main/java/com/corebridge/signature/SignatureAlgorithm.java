package com.corebridge.signature;

import com.corebridge.core.contracts.Algorithm;

/**
 * An enum representing common digital signature algorithms.
 */
public enum SignatureAlgorithm implements Algorithm {
    /**
     * SHA-256 with RSA.
     */
    SHA_256_WITH_RSA("SHA256withRSA"),
    /**
     * SHA-512 with RSA.
     */
    SHA_512_WITH_RSA("SHA512withRSA");

    private final String algorithm;

    /**
     * Constructs a new SignatureAlgorithm with the specified algorithm name.
     *
     * @param algorithm The name of the algorithm.
     */
    SignatureAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}