package com.corebridge.key.contracts;

import com.corebridge.core.contracts.Algorithm;

/**
 * An interface for classes that manage cryptographic keys.
 *
 * @param <T> The type of key that this manager handles (e.g., KeyPair, SecretKey).
 */
public interface KeyManager<T> extends Algorithm {

    /**
     * Generates a new cryptographic key or key pair.
     *
     * @return The generated key or key pair.
     */
    T generateKey();
}