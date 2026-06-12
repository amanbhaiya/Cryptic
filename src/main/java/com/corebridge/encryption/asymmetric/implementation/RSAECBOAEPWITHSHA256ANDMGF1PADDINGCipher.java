package com.corebridge.encryption.asymmetric.implementation;

import com.corebridge.core.utils.Base64Util;
import com.corebridge.encryption.asymmetric.contracts.AsymmetricCipher;
import com.corebridge.encryption.enums.AsymmetricTransformation;
import com.corebridge.core.exceptions.DecryptionException;
import com.corebridge.core.exceptions.EncryptionException;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This class implements the AsymmetricCipher interface using the RSA algorithm with OAEP padding.
 * It provides methods for encrypting data with a public key and decrypting data with a private key.
 */
public class RSAECBOAEPWITHSHA256ANDMGF1PADDINGCipher implements AsymmetricCipher {
    private final AsymmetricTransformation transformation;

    /**
     * Constructs a new cipher instance for the RSA/ECB/OAEPWithSHA-256AndMGF1Padding transformation.
     */
    public RSAECBOAEPWITHSHA256ANDMGF1PADDINGCipher() {
        this.transformation = AsymmetricTransformation.RSA_ECB_OAEP_WITH_SHA_256_AND_MGF1_PADDING;
    }

    /**
     * Encrypts the given data using the provided public key.
     *
     * @param data      The data to encrypt.
     * @param publicKey The public key in Base64 encoded format.
     * @return The encrypted data as a Base64 encoded string.
     * @throws EncryptionException if an error occurs during encryption.
     */
    @Override
    public String encrypt(String data, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(transformation.getTransformation());
            PublicKey key = KeyFactory.getInstance(getAlgorithm()).generatePublic(new X509EncodedKeySpec(Base64Util.decodeToBytes(publicKey)));
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64Util.encode(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | BadPaddingException |
                 IllegalBlockSizeException | java.security.InvalidKeyException e) {
            throw new EncryptionException(String.format("Failed to encrypt data using transformation '%s'", getTransformation()), e);
        }
    }

    /**
     * Decrypts the given data using the provided private key.
     *
     * @param data       The encrypted data as a Base64 encoded string.
     * @param privateKey The private key in Base64 encoded format.
     * @return The decrypted data.
     * @throws DecryptionException if an error occurs during decryption.
     */
    @Override
    public String decrypt(String data, String privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(transformation.getTransformation());
            PrivateKey key = KeyFactory.getInstance(getAlgorithm()).generatePrivate(new PKCS8EncodedKeySpec(Base64Util.decodeToBytes(privateKey)));
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64Util.decodeToBytes(data));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | BadPaddingException |
                 IllegalBlockSizeException | java.security.InvalidKeyException e) {
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