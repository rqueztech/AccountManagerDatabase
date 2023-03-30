package com.rqueztech.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// The password encryption class will implement the PBKDF2 Encryption algorithm.
// The implementation is very tricky due to the fact that passwords are stored as
// char[] and the salts are made as byte[]. Beware.
public class PasswordEncryption {

	// Iterate 10000 times with a key length of 128.
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    // Generate the salt using the secure random class.
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        
        // Create salt of 16 bytes, translating to 128 characters.
        byte[] salt = new byte[16];
        
        random.nextBytes(salt);
        return salt;
    }

    public byte[] hashPassword(char[] password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage());
        }
    }

    // Validate the password 
    public boolean validatePassword(char[] password, byte[] salt, byte[] expectedHash) {
        
    	// The current hash will be created with the password and salt given.
    	// This hash will be used to compare with the hash that exists for the
    	// Respective account
    	byte[] currentHash = hashPassword(password, salt);
        
        // If the current hash length doesn't match the expected hash, immediately it can
    	// Be assumed that it is not the hash.
        if (currentHash.length != expectedHash.length) {
            return false;
        }
        
        // Iterate through the two arrays. This will get the byte values and compare
        // Them iteratively. This CAN NOT be done by using toString(), since this will
        // Print a reference to the arrays. Iterative methods are required for byte[] and
        // char[].
        for (int i = 0; i < currentHash.length; i++) {
            if (currentHash[i] != expectedHash[i]) {
                return false;
            }
        }
        return true;
    }

}