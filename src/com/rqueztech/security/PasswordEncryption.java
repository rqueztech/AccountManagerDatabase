package com.rqueztech.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// The password encryption class will implement the PBKDF2 Encryption algorithm.
// The implementation is very tricky due to the fact that passwords are stored as
// char[] and the salts are made as byte[]. Beware.
class PasswordEncryption {

	// Iterate 10000 times with a key length of 128.
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    // Generate the salt using the secure random class.
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        
        // Create salt of 16 bytes, translating to 128 characters.
        byte[] salt = new byte[16];
        
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(char[] password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage());
        }
    }
    
}