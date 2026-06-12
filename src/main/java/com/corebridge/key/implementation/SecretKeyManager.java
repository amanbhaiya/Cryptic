package com.corebridge.key.implementation;

import com.corebridge.core.exceptions.KeyException;
import com.corebridge.core.utils.Base64Util;
import com.corebridge.key.contracts.KeyManager;
import com.corebridge.key.enums.KeyAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * A manager class for handling symmetric secret keys.
 */
public class SecretKeyManager implements KeyManager<SecretKey> {
    private final KeyAlgorithm algorithm;
    private final int keySize;

    /**
     * Constructs a new SecretKeyManager with the specified algorithm and key size.
     *
     * @param algorithm The key algorithm to use.
     * @param keySize   The size of the key in bits.
     */
    public SecretKeyManager(KeyAlgorithm algorithm, int keySize) {
        this.algorithm = algorithm;
        this.keySize = keySize;
    }

    /**
     * Generates a new secret key.
     *
     * @return The generated secret key.
     * @throws KeyException if the algorithm is not supported.
     */
    @Override
    public SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(getAlgorithm());
            keyGenerator.init(keySize);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new KeyException("Unsupported algorithm: " + getAlgorithm(), e);
        }
    }

    /**
     * Creates a secret key from a Base64-encoded string.
     *
     * @param key The Base64-encoded secret key.
     * @return The secret key.
     */
    public SecretKey getSecretKey(String key) {
        return new SecretKeySpec(Base64Util.decodeToBytes(key), getAlgorithm());
    }

    @Override
    public String getAlgorithm() {
        return algorithm.getAlgorithm();
    }
}