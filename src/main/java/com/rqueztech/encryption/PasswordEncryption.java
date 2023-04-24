package com.rqueztech.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// The password encryption class will implement the PBKDF2 Encryption algorithm.
public class PasswordEncryption {

	// Follow NIST recommendations of iterations and key length
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    // Method called outside of the current
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();

        // Create salt of 16 bytes, translating to 128 characters.
        byte[] salt = new byte[16];

        random.nextBytes(salt);
        return salt;
    }

    // Return the hash of the password. Password is in the form of a char[].
    // The salt is generated by the calling function and passed in as an argument
    public static byte[] hashPassword(char[] password, byte[] salt) {

        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);

        try {
            SecretKeyFactory factory = SecretKeyFactory
            		.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password: "
            		+ e.getMessage());
        }
    }

    // Validate the password by hashing it through the algorithm using the password entered
    // With the stored salt value.
    public static boolean validateEnteredPassword(char[] enteredPassword,
    		byte[] storedSaltValue, byte[] storedPasswordHash) {

    	// Hash the newly entered password.
    	byte[] enteredPasswordHash = hashPassword(
    			enteredPassword, storedSaltValue);

    	// If the password hashes match, the authtentication is complete.
        return Arrays.equals(enteredPasswordHash, storedPasswordHash);
    }

}