package com.corebridge.encryption.enums;

/**
 * An enum representing common encryption padding schemes.
 */
public enum EncryptionPaddingSchema {
    /**
     * No padding.
     */
    NO_PADDING("NoPadding"),
    /**
     * Optimal Asymmetric Encryption Padding with SHA-256 and MGF1.
     */
    OAEP_WITH_SHA_256_AND_MGF1_PADDING("OAEPWithSHA-256AndMGF1Padding");

    private final String paddingSchema;

    /**
     * Constructs a new EncryptionPaddingSchema with the specified padding schema name.
     *
     * @param paddingSchema The name of the padding schema.
     */
    EncryptionPaddingSchema(String paddingSchema) {
        this.paddingSchema = paddingSchema;
    }

    /**
     * Returns the standard name of the padding schema.
     *
     * @return The padding schema name.
     */
    public String getPaddingSchema() {
        return paddingSchema;
    }
}