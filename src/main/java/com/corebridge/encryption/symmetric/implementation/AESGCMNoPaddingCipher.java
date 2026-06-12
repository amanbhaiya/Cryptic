package com.corebridge.encryption.symmetric.implementation;

import com.corebridge.core.utils.Base64Util;
import com.corebridge.encryption.enums.SymmetricTransformation;
import com.corebridge.encryption.symmetric.contracts.SymmetricCipher;
import com.corebridge.core.exceptions.DecryptionException;
import com.corebridge.core.exceptions.EncryptionException;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * This class implements the SymmetricCipher interface using the AES/GCM/NoPadding algorithm.
 * It provides methods for encrypting and decrypting data using a secret key.
 */
public class AESGCMNoPaddingCipher implements SymmetricCipher {
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;
    private final SymmetricTransformation transformation;

    /**
     * Constructs a new cipher instance for the AES/GCM/NoPadding transformation.
     */
    public AESGCMNoPaddingCipher() {
        this.transformation = SymmetricTransformation.AES_GCM_NO_PADDING;
    }

    /**
     * Encrypts the given data using the provided secret key.
     *
     * @param data      The data to encrypt.
     * @param secretKey The secret key to use for encryption.
     * @return The encrypted data as a Base64 encoded string.
     * @throws EncryptionException if an error occurs during encryption.
     */
    @Override
    public String encrypt(String data, SecretKey secretKey) {
        try {
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            Cipher cipher = Cipher.getInstance(transformation.getTransformation());
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);
            return Base64Util.encode(combined);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new EncryptionException(String.format("Failed to encrypt data using transformation '%s'", getTransformation()), e);
        }
    }

    /**
     * Decrypts the given data using the provided secret key.
     *
     * @param encryptedData The encrypted data as a Base64 encoded string.
     * @param secretKey     The secret key to use for decryption.
     * @return The decrypted data.
     * @throws DecryptionException if an error occurs during decryption.
     */
    @Override
    public String decrypt(String encryptedData, SecretKey secretKey) {
        try {
            byte[] decoded = Base64Util.decodeToBytes(encryptedData);
            byte[] iv = new byte[GCM_IV_LENGTH];
            System.arraycopy(decoded, 0, iv, 0, iv.length);
            Cipher cipher = Cipher.getInstance(transformation.getTransformation());
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(decoded, iv.length, decoded.length - iv.length);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new DecryptionException(String.format("Failed to decrypt data using transformation '%s'", getTransformation()), e);
        }
    }

    @Override
    public String getMode() {
        return this.transformation.getMode();
    }

    @Override
    public String getPaddingScheme() {
        return this.transformation.getPaddingScheme();
    }

    @Override
    public String getAlgorithm() {
        return this.transformation.getAlgorithm();
    }
}