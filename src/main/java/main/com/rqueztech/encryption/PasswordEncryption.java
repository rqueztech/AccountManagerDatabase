package main.com.rqueztech.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Responsible for all of the necessary functions to encrypt the user passwordusing PBKDF2
 with SHA256.
 */
public class PasswordEncryption {

  // Follow NIST recommendations of iterations and key length
  private static final int ITERATIONS = 10000;
  private static final int KEY_LENGTH = 128;

  // Method called outside of the current

  /**
   * Generates a salt to be used to encrypt the user password.
   *
   * @return return 16 byte salt used to encrypt the users password.
   */
  public static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();

    // Create salt of 16 bytes, translating to 128 characters.
    byte[] salt = new byte[16];

    random.nextBytes(salt);
    return salt;
  }

  /**
   * Encrypts the employees password along with a salt according to NIST recommendations.
   *
   * @param password takes the password of the current account as (a char array)
   *
   * @param salt takes the
   *
   * @throws NoSuchAlgorithmException throw exception if algorithm is not found
   in environment.
   *
   * @throws InvalidKeySpecException throw exception if the encryption key
   is invalid.
   */
  public static byte[] hashPassword(char[] password, byte[] salt) {

    KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);

    try {
      SecretKeyFactory factory = SecretKeyFactory
              .getInstance("PBKDF2WithHmacSHA256");
      byte[] hash = factory.generateSecret(spec).getEncoded();
      return hash;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Error hashing password: " + e.getMessage());
    }
  }

  // Validate the password by hashing it through the algorithm using the password entered
  // With the stored salt value.
  public static boolean validateEnteredPassword(char[] enteredPassword,
      byte[] storedSaltValue, byte[] storedPasswordHash) {

    // hash the newly entered password.
    byte[] enteredPasswordHash = hashPassword(
      enteredPassword, storedSaltValue);

    // If the password hashes match, the authtentication is complete.
    return Arrays.equals(enteredPasswordHash, storedPasswordHash);
  }

}