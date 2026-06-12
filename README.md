# Cryptic: A Modern Java Cryptography Wrapper Library

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

Cryptic is a lightweight and easy-to-use Java library that simplifies common cryptographic operations. It provides a high-level, fluent API for hashing, digital signatures, and both symmetric and asymmetric encryption, making it easy to secure your applications without needing to dive into the complexities of the Java Cryptography Architecture (JCA).

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Core Concepts](#core-concepts)
- [Usage Examples](#usage-examples)
  - [Hashing](#1-hashing)
  - [Digital Signatures](#2-digital-signatures)
  - [Symmetric Encryption](#3-symmetric-encryption)
  - [Asymmetric Encryption](#4-asymmetric-encryption)
  - [Asymmetric Encryption with Digital Signatures](#5-asymmetric-encryption-with-digital-signatures)
- [Error Handling](#error-handling)
- [API Reference](#api-reference)

## Features

- **Simplified API**: An intuitive and fluent interface that abstracts away the boilerplate of JCA.
- **Type-Safe Configuration**: Uses enums for algorithms, modes, and padding schemes to prevent common errors.
- **Custom Exceptions**: Provides specific exceptions for different cryptographic failures, making error handling straightforward.
- **Modern Algorithms**: Comes pre-configured with secure, modern algorithms like AES-GCM and SHA-256.

## Installation

### Maven

To add Cryptic to your Maven project, add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>io.github.amanbhaiya</groupId>
  <artifactId>cryptic</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle

For Gradle projects, add the following to your `build.gradle` file:

```groovy
implementation 'io.github.amanbhaiya:cryptic:1.0.0'
```

## Core Concepts

The library is built around a few core concepts:

- **Managers**: High-level classes for performing cryptographic operations (e.g., `SignatureManager`, `KeyPairManager`).
- **Contracts**: A set of interfaces (`Algorithm`, `Transformation`) that define the basic behavior of cryptographic components.
- **Enums**: Type-safe enumerations for specifying algorithms, modes, and padding schemes.
- **Custom Exceptions**: Specific exceptions (`HashingException`, `SignatureException`, etc.) that provide clear error messages.

## Usage Examples

Here are some examples of how to use the library for common tasks.

### 1. Hashing

Hashing is a one-way process that generates a unique, fixed-size string from any given input.

```java
import com.corebridge.hash.enums.HashingAlgorithm;
import com.corebridge.hash.implementation.DefaultHashFunction;

DefaultHashFunction hashFunction = new DefaultHashFunction(HashingAlgorithm.SHA_256);
String dataToHash = "This is the data to be hashed.";
String hash = hashFunction.hash(dataToHash);
System.out.println("Generated Hash: " + hash);

boolean isHashVerified = hashFunction.verify(hash, dataToHash);
System.out.println("Hash Verified: " + isHashVerified);
```

### 2. Digital Signatures

Digital signatures verify the authenticity and integrity of a message.

```java
import com.corebridge.key.implementation.KeyPairManager;
import com.corebridge.signature.SignatureManager;
import com.corebridge.signature.enums.SignatureAlgorithm;
import java.security.KeyPair;

KeyPairManager keyPairManager = new KeyPairManager(KeyAlgorithm.RSA, 2048);
KeyPair keyPair = keyPairManager.generateKey();
SignatureManager signatureManager = new SignatureManager(SignatureAlgorithm.SHA256_WITH_RSA);
String dataToSign = "This is the data to be signed.";

String signature = signatureManager.sign(dataToSign, keyPair.getPrivate());
System.out.println("Generated Signature: " + signature);

boolean isSignatureVerified = signatureManager.verify(dataToSign, signature, keyPair.getPublic());
System.out.println("Signature Verified: " + isSignatureVerified);
```

### 3. Symmetric Encryption

Symmetric encryption uses the same key for both encryption and decryption.

```java
import com.corebridge.encryption.symmetric.implementation.AESGCMNoPaddingCipher;
import com.corebridge.key.implementation.SecretKeyManager;
import javax.crypto.SecretKey;

SecretKeyManager secretKeyManager = new SecretKeyManager(KeyAlgorithm.AES, 256);
SecretKey secretKey = secretKeyManager.generateKey();
AESGCMNoPaddingCipher aesCipher = new AESGCMNoPaddingCipher();
String originalData = "This is a secret message!";

String encryptedData = aesCipher.encrypt(originalData, secretKey);
System.out.println("Encrypted Data: " + encryptedData);

String decryptedData = aesCipher.decrypt(encryptedData, secretKey);
System.out.println("Decrypted Data: " + decryptedData);
```

### 4. Asymmetric Encryption

Asymmetric encryption uses a public key to encrypt and a private key to decrypt.

```java
import com.corebridge.encryption.asymmetric.implementation.RSAECBOAEPWITHSHA256ANDMGF1PADDINGCipher;
import com.corebridge.key.implementation.KeyPairManager;
import java.security.KeyPair;

KeyPairManager keyPairManager = new KeyPairManager(KeyAlgorithm.RSA, 2048);
KeyPair keyPair = keyPairManager.generateKey();
String publicKeyStr = Base64Util.encode(keyPair.getPublic().getEncoded());
String privateKeyStr = Base64Util.encode(keyPair.getPrivate().getEncoded());
RSAECBOAEPWITHSHA256ANDMGF1PADDINGCipher rsaCipher = new RSAECBOAEPWITHSHA256ANDMGF1PADDINGCipher();
String originalData = "This is a secret message!";

String encryptedData = rsaCipher.encrypt(originalData, publicKeyStr);
System.out.println("Encrypted Data: " + encryptedData);

String decryptedData = rsaCipher.decrypt(encryptedData, privateKeyStr);
System.out.println("Decrypted Data: " + decryptedData);
```

### 5. Asymmetric Encryption with Digital Signatures

Combine encryption and signatures for confidentiality, authenticity, and integrity.

```java
// (Assumes rsaCipher, keyPair, publicKeyStr, and privateKeyStr from the previous examples are available)

String secretMessage = "This is a highly confidential and authenticated message!";
String encryptedMessage = rsaCipher.encrypt(secretMessage, publicKeyStr);

SignatureManager signatureManager = new SignatureManager(SignatureAlgorithm.SHA256_WITH_RSA);
String signature = signatureManager.sign(encryptedMessage, keyPair.getPrivate());

// On the receiver's end:
boolean isSignatureVerified = signatureManager.verify(encryptedMessage, signature, keyPair.getPublic());

if (isSignatureVerified) {
    String decryptedMessage = rsaCipher.decrypt(encryptedMessage, privateKeyStr);
    System.out.println("Decrypted Message: " + decryptedMessage);
}
```

## Error Handling

Cryptic uses a set of custom runtime exceptions to signal errors. This allows you to write cleaner code and handle specific failures gracefully.

```java
try {
    String signature = signatureManager.sign(data, privateKey);
    // ...
} catch (SignatureException e) {
    System.err.println("Failed to sign data: " + e.getMessage());
    // Handle the error
}
```

The primary exceptions are:
- `HashingException`
- `SignatureException`
- `EncryptionException`
- `DecryptionException`
- `KeyException`

## API Reference

The library uses enums to ensure you are using valid and secure algorithms and transformations.

- **`HashingAlgorithm`**: `MD5`, `SHA_1`, `SHA_256`, `SHA_384`, `SHA_512`
- **`SignatureAlgorithm`**: `SHA256_WITH_RSA`, `SHA512_WITH_RSA`
- **`KeyAlgorithm`**: `AES`, `RSA`
- **`SymmetricTransformation`**: `AES_GCM_NO_PADDING`
- **`AsymmetricTransformation`**: `RSA_ECB_OAEP_WITH_SHA_256_AND_MGF1_PADDING`
