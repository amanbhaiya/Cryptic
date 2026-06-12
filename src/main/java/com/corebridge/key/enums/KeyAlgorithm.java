package com.corebridge.key.enums;

import com.corebridge.core.contracts.Algorithm;

/**
 * An enum representing common algorithms for cryptographic key generation.
 */
public enum KeyAlgorithm implements Algorithm {
    /**
     * Advanced Encryption Standard. Used for generating symmetric keys.
     */
    AES("AES"),
    /**
     * Rivest-Shamir-Adleman. Used for generating asymmetric key pairs.
     */
    RSA("RSA");

    private final String algorithm;

    /**
     * Constructs a new KeyAlgorithm with the specified algorithm name.
     *
     * @param algorithm The name of the algorithm.
     */
    KeyAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}