package com.rqueztech.ui.validation;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {
	
	private static final Pattern PASSWORD_REQUIREMENTS_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}");
	private static final Pattern LEGAL_CHARACTERS_PATTERN = Pattern.compile("^[A-Za-z0-9@$!%*#?&]+$");
	private static final Pattern ONLY_LETTERS_AND_NUMBERS_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
	private static final Pattern ONLY_LETTER_CHARACTERS_PATTERN = Pattern.compile("^([A-Za-z]+){2,}$");
	private static final Pattern NO_UPPERCASE_CHARACTERS_PATTERN = Pattern.compile("^[^A-Z]+$");
	private static final Pattern NO_LOWERCASE_CHARACTERS_PATTERN = Pattern.compile("^[^a-z]+$");
	private static final Pattern NO_SPECIAL_CHARACTERS_PATTERN = Pattern.compile("^[^@$!%*#?&]+$");
	private static final Pattern NO_NUMBERS_PATTERN = Pattern.compile("^[^0-9]+$");
	
	// IN WORKS
	private static final Pattern NO_ENTRY_FOUND_PATTERN = Pattern.compile("^$");

	// ------------------------------------------------------------------------------------
	private boolean matchRegex(Pattern pattern, CharBuffer input) {
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	// ------------------------------------------------------------------------------------
	private boolean matchPasswordRegex(Pattern pattern, CharBuffer isMeetsPasswordRequirements) {
		Matcher matcher = pattern.matcher(isMeetsPasswordRequirements);
		return matcher.matches();
	}
	
	// ------------------------------------------------------------------------------------
	public boolean noEntryFound(char[] inputMeetsPasswordRequirements) {
		// If the password requirements are not met, print a message and then give specific feedback
		CharBuffer inputBuffer = CharBuffer.wrap(inputMeetsPasswordRequirements);
		return matchRegex(NO_ENTRY_FOUND_PATTERN, inputBuffer);
	}
	
	// ------------------------------------------------------------------------------------
	public boolean validatePassword(char[] inputMeetsPasswordRequirements) {
		// If the password requirements are not met, print a message and then give specific feedback
		CharBuffer inputBuffer = CharBuffer.wrap(inputMeetsPasswordRequirements);
		return matchPasswordRegex(PASSWORD_REQUIREMENTS_PATTERN, inputBuffer);
	}
	
	// ------------------------------------------------------------------------------------
	public boolean containsLegalCharacters(char[] inputLegalCharacters) {
		// Return true or false (dependent on if the regex matches)
		CharBuffer inputBuffer = CharBuffer.wrap(inputLegalCharacters);
		return matchRegex(LEGAL_CHARACTERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	// Check so see if input entered by the user is legal. If all characters
	// Entered are legal, then it will be return true. Otherwise, returns false.
	public boolean isOnlyLettersAndNumbers(char[] inputLettersAndNumbers) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputLettersAndNumbers);
		return matchRegex(ONLY_LETTERS_AND_NUMBERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	// Check so see if input entered by the user is legal. If all characters
	// Entered are legal, then it will be return true. Otherwise, returns false.
	public boolean isOnlyLetterCharacters(char[] inputOnlyLetters) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputOnlyLetters);
		return matchRegex(ONLY_LETTER_CHARACTERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoUpperCaseCharacters(char[] inputNoUpperCase) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputNoUpperCase);
		return matchRegex(NO_UPPERCASE_CHARACTERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoLowerCaseCharacters(char[] inputNoLowerCase) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputNoLowerCase);
		return matchRegex(NO_LOWERCASE_CHARACTERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoSpecialCharacters(char[] inputNoSpecialCharacters) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputNoSpecialCharacters);
		return matchRegex(NO_SPECIAL_CHARACTERS_PATTERN, inputBuffer);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoNumbersFound(char[] inputNoNumbersFound) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputNoNumbersFound);
		return matchRegex(NO_NUMBERS_PATTERN, inputBuffer);
	}
	
	
}
