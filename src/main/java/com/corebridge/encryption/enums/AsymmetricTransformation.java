package com.corebridge.encryption.enums;

import com.corebridge.core.contracts.Transformation;

/**
 * An enum representing asymmetric cryptographic transformations.
 * Each transformation is a combination of an algorithm, a mode, and a padding scheme.
 */
public enum AsymmetricTransformation implements Transformation {
    /**
     * RSA encryption with ECB mode and OAEPWithSHA-256AndMGF1Padding padding.
     */
    RSA_ECB_OAEP_WITH_SHA_256_AND_MGF1_PADDING(EncryptionAlgorithm.RSA, EncryptionMode.ECB, EncryptionPaddingSchema.OAEP_WITH_SHA_256_AND_MGF1_PADDING);

    private final String algorithm;
    private final String mode;
    private final String paddingSchema;

    /**
     * Constructs a new AsymmetricTransformation with the specified algorithm, mode, and padding schema.
     *
     * @param encryptionAlgorithm The encryption algorithm.
     * @param mode                The mode of operation.
     * @param paddingSchema       The padding scheme.
     */
    AsymmetricTransformation(EncryptionAlgorithm encryptionAlgorithm, EncryptionMode mode, EncryptionPaddingSchema paddingSchema) {
        this.algorithm = encryptionAlgorithm.getAlgorithm();
        this.mode = mode.getMode();
        this.paddingSchema = paddingSchema.getPaddingSchema();
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override
    public String getMode() {
        return this.mode;
    }

    @Override
    public String getPaddingScheme() {
        return this.paddingSchema;
    }
}