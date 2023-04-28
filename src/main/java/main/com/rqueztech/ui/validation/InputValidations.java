package main.com.rqueztech.ui.validation;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class provides methods for input validation to ensure that only legal
 * data is ingested into the system.
 *
 * @author Ricardo Quezada
 *
 */

public class InputValidations {

  private static final Pattern PASSWORDREQUIREMENTSPATTERN =
      Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])"
          + "[A-Za-z\\d@$!%*#?&]{8,}");

  private static final Pattern LEGALCHARACTERSPATTERN =
      Pattern.compile("^[A-Za-z0-9@$!%*#?&]+$");

  private static final Pattern ONLYLETTERSANDNUMBERSPATTERN =
      Pattern.compile("^[A-Za-z0-9]+$");

  private static final Pattern ONLYLETTERCHARACTERSPATTERN =
      Pattern.compile("^([A-Za-z]+){2,}$");

  private static final Pattern NOUPPERCASECHARACTERSPATTERN =
      Pattern.compile("^[^A-Z]+$");

  private static final Pattern NOLOWERCASECHARACTERSPATTERN =
      Pattern.compile("^[^a-z]+$");

  private static final Pattern NOSPECIALCHARACTERSPATTERN =
      Pattern.compile("^[^@$!%*#?&]+$");

  private static final Pattern NONUMBERSPATTERN =
      Pattern.compile("^[^0-9]+$");

  // IN WORKS
  private static final Pattern NOENTRYFOUNDPATTERN = Pattern.compile("^$");

  // --------------------------------------------------------------------------
  private boolean matchRegex(Pattern pattern, CharBuffer input) {
    Matcher matcher = pattern.matcher(input);
    return matcher.matches();
  }

  //--------------------------------------------------------------------------
  private boolean matchRegex(Pattern pattern, String input) {
    Matcher matcher = pattern.matcher(input);
    return matcher.matches();
  }

  // --------------------------------------------------------------------------
  private boolean matchPasswordRegex(Pattern pattern,
      CharBuffer isMeetsPasswordRequirements) {
    Matcher matcher = pattern.matcher(isMeetsPasswordRequirements);
    return matcher.matches();
  }

  private boolean matchPasswordRegex(Pattern pattern,
      String isMeetsPasswordRequirements) {

    Matcher matcher = pattern.matcher(isMeetsPasswordRequirements);
    return matcher.matches();
  }

  /**
   * Checks whether the given input meets password requirements.
   *
   * @param inputMeetsPasswordRequirements A char array representing the password input to check.
   * @return true if the input meets the password requirements, false otherwise.
   */
  // --------------------------------------------------------------------------
  public boolean noEntryFound(char[] inputMeetsPasswordRequirements) {
    // If the password requirements are not met, print a message and
    // then give specific feedback
    CharBuffer inputBuffer =
        CharBuffer.wrap(inputMeetsPasswordRequirements);

    return matchRegex(NOENTRYFOUNDPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean noEntryFound(String inputMeetsPasswordRequirements) {
    return matchRegex(NOENTRYFOUNDPATTERN, inputMeetsPasswordRequirements);
  }

  /**
   * This method validates if the given password meets the set of requirements.
   If the password requirements are not met, it returns as false.
   *
   * @param inputMeetsPasswordRequirements the password that needs to be
   validated, as a character array
   *
   * @return true if the password meets the requirements, false otherwise
  */
  public boolean validatePassword(char[] inputMeetsPasswordRequirements) {
    // If the password requirements are not met, print a message and then
    // give specific feedback
    CharBuffer inputBuffer = CharBuffer.wrap(inputMeetsPasswordRequirements);
    return matchPasswordRegex(PASSWORDREQUIREMENTSPATTERN, inputBuffer);
  }

  /**
   * This method validates if the given password meets the set of requirements.
   If the password requirements are not met, it returns as false.
   *
   * @param inputMeetsPasswordRequirements the password that needs to be
   validated, as (a String)
   *
   * @return true if the password meets the requirements, false otherwise
  */
  // --------------------------------------------------------------------------
  public boolean validatePassword(String inputMeetsPasswordRequirements) {
    // If the password requirements are not met, print a message and then
    // give specific feedback
    return matchPasswordRegex(PASSWORDREQUIREMENTSPATTERN,
      inputMeetsPasswordRequirements);
  }


  /**
   * This method validates if the given text input contains legal characters.
   If the requirements are not met, it returns false.
   *
   * @param inputLegalCharacters the password that needs to be
   validated, as (a char array)
   *
   * @return true if the password meets the requirements, false otherwise
  */
  // --------------------------------------------------------------------------
  public boolean containsLegalCharacters(char[] inputLegalCharacters) {
    // Return true or false (dependent on if the regex matches)
    CharBuffer inputBuffer = CharBuffer.wrap(inputLegalCharacters);
    return matchRegex(LEGALCHARACTERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean containsLegalCharacters(String inputLegalCharacters) {
    // Return true or false (dependent on if the regex matches)
    return matchRegex(LEGALCHARACTERSPATTERN, inputLegalCharacters);
  }

  // --------------------------------------------------------------------------
  // Check so see if input entered by the user is legal. If all characters
  // Entered are legal, then it will be return true. Otherwise, returns false.
  public boolean isOnlyLettersAndNumbers(char[] inputLettersAndNumbers) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputLettersAndNumbers);
    return matchRegex(ONLYLETTERSANDNUMBERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  // Check so see if input entered by the user is legal. If all characters
  // Entered are legal, then it will be return true. Otherwise, returns false
  public boolean isOnlyLettersAndNumbers(String inputLettersAndNumbers) {
    return matchRegex(ONLYLETTERSANDNUMBERSPATTERN, inputLettersAndNumbers);
  }

  // --------------------------------------------------------------------------
  // Check so see if input entered by the user is legal. If all characters
  // Entered are legal, then it will be return true. Otherwise, returns false
  public boolean isOnlyLetterCharacters(char[] inputOnlyLetters) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputOnlyLetters);
    return matchRegex(ONLYLETTERCHARACTERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  // Check so see if input entered by the user is legal. If all characters
  // Entered are legal, then it will be return true. Otherwise, returns false
  public boolean isOnlyLetterCharacters(String inputOnlyLetters) {
    return matchRegex(ONLYLETTERCHARACTERSPATTERN, inputOnlyLetters);
  }

  // --------------------------------------------------------------------------
  public boolean isNoUpperCaseCharacters(char[] inputNoUpperCase) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputNoUpperCase);
    return matchRegex(NOUPPERCASECHARACTERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean isNoUpperCaseCharacters(String inputNoUpperCase) {
    return matchRegex(NOUPPERCASECHARACTERSPATTERN, inputNoUpperCase);
  }

  // --------------------------------------------------------------------------
  public boolean isNoLowerCaseCharacters(char[] inputNoLowerCase) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputNoLowerCase);
    return matchRegex(NOLOWERCASECHARACTERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean isNoLowerCaseCharacters(String inputNoLowerCase) {
    return matchRegex(NOLOWERCASECHARACTERSPATTERN, inputNoLowerCase);
  }

  // --------------------------------------------------------------------------
  public boolean isNoSpecialCharacters(char[] inputNoSpecialCharacters) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputNoSpecialCharacters);
    return matchRegex(NOSPECIALCHARACTERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean isNoSpecialCharacters(String inputNoSpecialCharacters) {
    return matchRegex(NOSPECIALCHARACTERSPATTERN, inputNoSpecialCharacters);
  }

  // --------------------------------------------------------------------------
  public boolean isNoNumbersFound(char[] inputNoNumbersFound) {
    CharBuffer inputBuffer = CharBuffer.wrap(inputNoNumbersFound);
    return matchRegex(NONUMBERSPATTERN, inputBuffer);
  }

  // --------------------------------------------------------------------------
  public boolean isNoNumbersFound(String inputNoNumbersFound) {
    return matchRegex(NONUMBERSPATTERN, inputNoNumbersFound);
  }
}
