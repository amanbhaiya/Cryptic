package com.corebridge.core.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A utility class for handling Base64 encoding and decoding, as well as other
 * common data format conversions used in cryptographic operations.
 * <p>
 * This class is final and cannot be instantiated.
 */
public final class Base64Util {

    private Base64Util() {
        // Prevent instantiation of this utility class.
    }

    /**
     * Base64-encodes a string using the UTF-8 charset.
     *
     * @param data The string to encode.
     * @return A Base64-encoded string.
     */
    public static String encode(String data) {
        return encode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64-encodes a byte array into a string.
     *
     * @param data The byte array to encode.
     * @return A Base64-encoded string.
     */
    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * Base64-encodes a byte array into a Base64-encoded byte array.
     *
     * @param data The byte array to encode.
     * @return A Base64-encoded byte array.
     */
    public static byte[] encodeToBytes(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    /**
     * Decodes a Base64-encoded string into a string using the UTF-8 charset.
     *
     * @param base64Data The Base64-encoded string to decode.
     * @return The decoded string.
     */
    public static String decode(String base64Data) {
        return new String(decodeToBytes(base64Data), StandardCharsets.UTF_8);
    }

    /**
     * Decodes a Base64-encoded string into a string using the specified charset.
     *
     * @param base64Data The Base64-encoded string to decode.
     * @param charset    The charset to use for the output string.
     * @return The decoded string.
     */
    public static String decode(String base64Data, Charset charset) {
        return new String(decodeToBytes(base64Data), charset);
    }

    /**
     * Decodes a Base64-encoded byte array into a string using the UTF-8 charset.
     *
     * @param base64Data The Base64-encoded byte array to decode.
     * @return The decoded string.
     */
    public static String decode(byte[] base64Data) {
        return decode(base64Data, StandardCharsets.UTF_8);
    }

    /**
     * Decodes a Base64-encoded byte array into a string using the specified charset.
     *
     * @param base64Data The Base64-encoded byte array to decode.
     * @param charset    The charset to use for the output string.
     * @return The decoded string.
     */
    public static String decode(byte[] base64Data, Charset charset) {
        return new String(decodeToBytes(base64Data), charset);
    }

    /**
     * Decodes a Base64-encoded string into a byte array.
     *
     * @param base64Data The Base64-encoded string to decode.
     * @return The decoded byte array.
     */
    public static byte[] decodeToBytes(String base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }

    /**
     * Decodes a Base64-encoded byte array into a raw byte array.
     *
     * @param base64Data The Base64-encoded byte array to decode.
     * @return The decoded byte array.
     */
    public static byte[] decodeToBytes(byte[] base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }
}