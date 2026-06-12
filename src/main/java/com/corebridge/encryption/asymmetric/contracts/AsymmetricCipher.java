package com.corebridge.encryption.asymmetric.contracts;

import com.corebridge.core.contracts.Transformation;
import com.corebridge.core.exceptions.DecryptionException;
import com.corebridge.core.exceptions.EncryptionException;

/**
 * This interface defines the contract for an asymmetric cipher.
 * Asymmetric ciphers use a pair of keys, a public key and a private key, to encrypt and decrypt data.
 * The public key can be shared with anyone, while the private key must be kept secret.
 */
public interface AsymmetricCipher extends Transformation {

    /**
     * Encrypts the given data using the public key.
     *
     * @param data      The data to encrypt.
     * @param publicKey The public key to use for encryption.
     * @return The encrypted data.
     */
    String encrypt(String data, String publicKey) throws EncryptionException;

    /**
     * Decrypts the given data using the private key.
     *
     * @param data       The data to decrypt.
     * @param privateKey The private key to use for decryption.
     * @return The decrypted data.
     */
    String decrypt(String data, String privateKey) throws DecryptionException;
}