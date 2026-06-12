package com.corebridge.encryption.enums;

/**
 * An enum representing common encryption modes of operation.
 */
public enum EncryptionMode {
    /**
     * Galois/Counter Mode.
     */
    GCM("GCM"),
    /**
     * Electronic Codebook Mode.
     */
    ECB("ECB");

    private final String mode;

    /**
     * Constructs a new EncryptionMode with the specified mode name.
     *
     * @param mode The name of the mode.
     */
    EncryptionMode(String mode) {
        this.mode = mode;
    }

    /**
     * Returns the standard name of the encryption mode.
     *
     * @return The mode name.
     */
    public String getMode() {
        return mode;
    }
}