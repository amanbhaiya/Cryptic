package com.corebridge.encryption.symmetric.contracts;

import com.corebridge.core.contracts.Transformation;
import com.corebridge.core.exceptions.DecryptionException;
import com.corebridge.core.exceptions.EncryptionException;

import javax.crypto.SecretKey;

/**
 * This interface defines the contract for a symmetric cipher.
 * Symmetric ciphers use the same key for both encryption and decryption.
 */
public interface SymmetricCipher extends Transformation {

    /**
     * Encrypts the given data using the provided secret key.
     *
     * @param data      The data to encrypt.
     * @param secretKey The secret key to use for encryption.
     * @return The encrypted data.
     */
    String encrypt(String data, SecretKey secretKey) throws EncryptionException;

    /**
     * Decrypts the given data using the provided secret key.
     *
     * @param encryptedData The data to decrypt.
     * @param secretKey     The secret key to use for decryption.
     * @return The decrypted data.
     */
    String decrypt(String encryptedData, SecretKey secretKey) throws DecryptionException;
}