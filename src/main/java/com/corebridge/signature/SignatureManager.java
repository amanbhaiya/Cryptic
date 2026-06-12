package com.corebridge.signature;

import com.corebridge.core.contracts.Algorithm;
import com.corebridge.core.exceptions.SignatureException;
import com.corebridge.core.utils.Base64Util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * A manager class for handling digital signatures, including signing and verification.
 */
public class SignatureManager implements Algorithm {

    private final SignatureAlgorithm algorithm;

    /**
     * Constructs a new SignatureManager with the specified signature algorithm.
     *
     * @param algorithm The signature algorithm to use.
     */
    public SignatureManager(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Signs the given data using the provided private key.
     *
     * @param data       The data to sign.
     * @param privateKey The private key to use for signing.
     * @return A Base64-encoded string representing the signature.
     * @throws SignatureException if an error occurs during the signing process.
     */
    public String sign(String data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(getAlgorithm());
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            return Base64Util.encode(signature.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | java.security.SignatureException e) {
            throw new SignatureException("Failed to sign data.", e);
        }
    }

    /**
     * Verifies the given signature against the original data and the public key.
     *
     * @param data           The original data that was signed.
     * @param signatureValue The Base64-encoded signature to verify.
     * @param publicKey      The public key corresponding to the private key used for signing.
     * @return {@code true} if the signature is valid, {@code false} otherwise.
     * @throws SignatureException if an error occurs during the verification process.
     */
    public boolean verify(String data, String signatureValue, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance(getAlgorithm());
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            return signature.verify(Base64Util.decodeToBytes(signatureValue));
        } catch (NoSuchAlgorithmException | InvalidKeyException | java.security.SignatureException e) {
            throw new SignatureException("Failed to verify signature.", e);
        }
    }

    @Override
    public String getAlgorithm() {
        return algorithm.getAlgorithm();
    }
}