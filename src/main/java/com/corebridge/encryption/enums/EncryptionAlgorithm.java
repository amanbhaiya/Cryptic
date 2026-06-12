package com.corebridge.encryption.enums;

import com.corebridge.core.contracts.Algorithm;

/**
 * An enum representing common encryption algorithms.
 */
public enum EncryptionAlgorithm implements Algorithm {
    /**
     * Advanced Encryption Standard.
     */
    AES("AES"),
    /**
     * Rivest-Shamir-Adleman.
     */
    RSA("RSA");

    private final String algorithm;

    /**
     * Constructs a new EncryptionAlgorithm with the specified algorithm name.
     *
     * @param algorithm The name of the algorithm.
     */
    EncryptionAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}