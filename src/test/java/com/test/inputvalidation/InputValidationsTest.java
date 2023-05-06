package com.test.inputvalidation;

import java.util.Random;
import main.com.rqueztech.ui.validation.InputValidations;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The class ensures that all input validation functions are doing the proper
 input validations and working as they should.
 */
public class InputValidationsTest { 
  @Test
  public void testNoEntryFoundWithString() {
    InputValidations inputValidations = new InputValidations();
    String input = "";
    boolean result = inputValidations.noEntryFound(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testValidatePasswordWithCharArray() {
    InputValidations inputValidations = new InputValidations();

    for (int counter = 0; counter < 40; counter++) {     
      String input = this.generatePassword();
      boolean result = inputValidations.validatePassword(input);
      Assert.assertTrue(result);
    }
  }

  @Test
  public void testValidatePasswordWithString() {
    InputValidations inputValidations = new InputValidations();

    for (int counter = 0; counter < 40; counter++) {
      String input = this.generatePassword();
      boolean result = inputValidations.validatePassword(input.toCharArray());
      Assert.assertTrue(result);
    }
  }

  /**
   * Generates a random password with all of the requirements for a valid
   password.
   *
   * @return returns valid password with all requirements as (a String)
   */
  public String generatePassword() {
    String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    String number = "0123456789";
    String legalCharacters = "@$!%*#?&"; 

    StringBuilder sb = new StringBuilder();
    Random random = new Random();

    sb.append(upperCase.charAt(random.nextInt(upperCase.length())));
    sb.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
    sb.append(number.charAt(random.nextInt(number.length())));
    sb.append(legalCharacters.charAt(random.nextInt(legalCharacters.length())));
    sb.append(upperCase.charAt(random.nextInt(upperCase.length())));
    sb.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
    sb.append(number.charAt(random.nextInt(number.length())));
    sb.append(legalCharacters.charAt(random.nextInt(legalCharacters.length())));

    System.out.println(sb.toString());
    
    return sb.toString();
  }

  @Test
  public void testContainsLegalCharactersWithCharArray() {
    InputValidations inputValidations = new InputValidations();
    char[] input = {'A', 'b', 'c', 'd', '1', '2', '3', '4', '$'};
    boolean result = inputValidations.containsLegalCharacters(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testContainsLegalCharactersWithString() {
    InputValidations inputValidations = new InputValidations();
    String input = "Abcd1234$";
    boolean result = inputValidations.containsLegalCharacters(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testIsOnlyLettersAndNumbersWithCharArray() {
    InputValidations inputValidations = new InputValidations();
    char[] input = {'A', 'b', 'c', 'd', '1', '2', '3', '4'};
    boolean result = inputValidations.isOnlyLettersAndNumbers(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testIsOnlyLettersAndNumbersWithString() {
    InputValidations inputValidations = new InputValidations();
    String input = "Abcd1234";
    boolean result = inputValidations.isOnlyLettersAndNumbers(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testIsOnlyLetterCharactersWithCharArray() {
    InputValidations inputValidations = new InputValidations();
    char[] input = {'A', 'b', 'c', 'd'};
    boolean result = inputValidations.isOnlyLetterCharacters(input);
    Assert.assertTrue(result);
  }

  @Test
  public void testIsOnlyLetterCharactersWithString() {
    InputValidations inputValidations = new InputValidations();
    String input = "Abcd";
    boolean result = inputValidations.isOnlyLetterCharacters(input);
    Assert.assertTrue(result);
  }
}
