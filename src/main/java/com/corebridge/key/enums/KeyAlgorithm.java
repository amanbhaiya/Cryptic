package com.corebridge.key.enums;

import com.corebridge.core.contracts.Algorithm;

/**
 * Supported algorithms for cryptographic key generation.
 *
 * <p>This enum includes algorithms for:
 * <ul>
 *     <li>Symmetric encryption (AES)</li>
 *     <li>Asymmetric encryption/signatures (RSA)</li>
 *     <li>HMAC signing (HmacSHA256, HmacSHA384, HmacSHA512)</li>
 * </ul>
 */
public enum KeyAlgorithm implements Algorithm {

    /**
     * Advanced Encryption Standard (symmetric encryption).
     */
    AES("AES"),

    /**
     * Rivest-Shamir-Adleman (asymmetric encryption/signatures).
     */
    RSA("RSA"),

    /**
     * HMAC using SHA-256.
     * Commonly used for JWT HS256 signing.
     */
    HMAC_SHA256("HmacSHA256"),

    /**
     * HMAC using SHA-384.
     * Commonly used for JWT HS384 signing.
     */
    HMAC_SHA384("HmacSHA384"),

    /**
     * HMAC using SHA-512.
     * Commonly used for JWT HS512 signing.
     */
    HMAC_SHA512("HmacSHA512");

    private final String algorithm;

    KeyAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }
}