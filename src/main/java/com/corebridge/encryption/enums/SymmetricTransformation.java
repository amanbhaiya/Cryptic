package com.corebridge.encryption.enums;

import com.corebridge.core.contracts.Transformation;

/**
 * An enum representing symmetric cryptographic transformations.
 * Each transformation is a combination of an algorithm, a mode, and a padding scheme.
 */
public enum SymmetricTransformation implements Transformation {
    /**
     * AES encryption with GCM mode and no padding.
     */
    AES_GCM_NO_PADDING(EncryptionAlgorithm.AES, EncryptionMode.GCM, EncryptionPaddingSchema.NO_PADDING);

    private final String algorithm;
    private final String mode;
    private final String paddingSchema;

    /**
     * Constructs a new SymmetricTransformation with the specified algorithm, mode, and padding schema.
     *
     * @param encryptionAlgorithm The encryption algorithm.
     * @param mode                The mode of operation.
     * @param paddingSchema       The padding scheme.
     */
    SymmetricTransformation(EncryptionAlgorithm encryptionAlgorithm, EncryptionMode mode, EncryptionPaddingSchema paddingSchema) {
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