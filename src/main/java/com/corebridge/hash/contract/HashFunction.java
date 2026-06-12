package com.corebridge.hash.contract;

import com.corebridge.core.contracts.Algorithm;
import com.corebridge.core.exceptions.HashingException;
import com.corebridge.core.exceptions.ValidationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * This interface defines the contract for a hash function.
 * A hash function is a mathematical algorithm that maps data of arbitrary size to a fixed-size string of characters.
 * It is a one-way function, meaning that it is computationally infeasible to reverse.
 */
public interface HashFunction extends Algorithm {

    /**
     * Hashes the given data.
     *
     * @param data The data to hash.
     * @return The hashed data.
     */
    default String hash(String data) throws HashingException, ValidationException {
        if (data == null || data.isBlank()) {
            throw new ValidationException("Data cannot be null or empty");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(getAlgorithm());
            messageDigest.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new HashingException("Unsupported algorithm: " + getAlgorithm(), e);
        }
    }

    /**
     * Verifies that the given hash matches the given data.
     *
     * @param hash The hash to verify.
     * @param data The data to verify.
     * @return True if the hash matches the data, false otherwise.
     */
    default boolean verify(String hash, String data) throws ValidationException, HashingException {
        if (hash == null || hash.isBlank()) {
            throw new ValidationException("Hash cannot be null or empty");
        }
        if (data == null || data.isBlank()) {
            throw new ValidationException("Data cannot be null or empty");
        }
        return hash(data).equals(hash);
    }
}