package com.corebridge.key.implementation;

import com.corebridge.core.exceptions.KeyException;
import com.corebridge.core.utils.Base64Util;
import com.corebridge.key.contracts.KeyManager;
import com.corebridge.key.enums.KeyAlgorithm;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * A manager class for handling asymmetric key pairs (public and private keys).
 */
public class KeyPairManager implements KeyManager<KeyPair> {
    private final KeyAlgorithm algorithm;
    private final int keySize;

    /**
     * Constructs a new KeyPairManager with the specified algorithm and key size.
     *
     * @param algorithm The key algorithm to use.
     * @param keySize   The size of the key in bits.
     */
    public KeyPairManager(KeyAlgorithm algorithm, int keySize) {
        this.algorithm = algorithm;
        this.keySize = keySize;
    }

    /**
     * Generates a new key pair.
     *
     * @return The generated key pair.
     * @throws KeyException if the algorithm is not supported.
     */
    @Override
    public KeyPair generateKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(getAlgorithm());
            generator.initialize(keySize);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new KeyException("Unsupported algorithm: " + getAlgorithm(), e);
        }
    }

    /**
     * Extracts the public key from a key pair.
     *
     * @param keyPair The key pair.
     * @return The public key.
     */
    public PublicKey getPublicKey(KeyPair keyPair) {
        return keyPair.getPublic();
    }

    /**
     * Creates a public key from a Base64-encoded string.
     *
     * @param key The Base64-encoded public key.
     * @return The public key.
     * @throws KeyException if the key is invalid.
     */
    public PublicKey getPublicKey(String key) {
        try {
            byte[] keyBytes = Base64Util.decodeToBytes(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance(getAlgorithm()).generatePublic(spec);
        } catch (Exception e) {
            throw new KeyException("Failed to create public key", e);
        }
    }

    /**
     * Extracts the private key from a key pair.
     *
     * @param keyPair The key pair.
     * @return The private key.
     */
    public PrivateKey getPrivateKey(KeyPair keyPair) {
        return keyPair.getPrivate();
    }

    /**
     * Creates a private key from a Base64-encoded string.
     *
     * @param key The Base64-encoded private key.
     * @return The private key.
     * @throws KeyException if the key is invalid.
     */
    public PrivateKey getPrivateKey(String key) {
        try {
            byte[] keyBytes = Base64Util.decodeToBytes(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance(getAlgorithm()).generatePrivate(spec);
        } catch (Exception e) {
            throw new KeyException("Failed to create private key", e);
        }
    }

    @Override
    public String getAlgorithm() {
        return algorithm.getAlgorithm();
    }
}