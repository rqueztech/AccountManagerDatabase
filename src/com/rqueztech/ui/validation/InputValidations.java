package com.rqueztech.ui.validation;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidations {
	
	private static final Pattern PASSWORD_REQUIREMENTS_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}");
	private static final Pattern LEGAL_CHARACTERS_PATTERN = Pattern.compile("^[A-Za-z0-9@$!%*#?&]+$");
	private static final Pattern ONLY_LETTERS_AND_NUMBERS_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
	private static final Pattern ONLY_LETTER_CHARACTERS_PATTERN = Pattern.compile("^[A-Za-z]+$");
	private static final Pattern NO_UPPERCASE_CHARACTERS_PATTERN = Pattern.compile("^[^A-Z]+$");
	private static final Pattern NO_LOWERCASE_CHARACTERS_PATTERN = Pattern.compile("^[^a-z]+$");
	private static final Pattern NO_SPECIAL_CHARACTERS_PATTERN = Pattern.compile("^[^@$!%*#?&]+$");
	private static final Pattern NO_NUMBERS_PATTERN = Pattern.compile("^[^0-9]+$");
	
	// IN WORKS
	private static final Pattern NO_ENTRY_FOUND_PATTERN = Pattern.compile("^$");

	// ------------------------------------------------------------------------------------
	private boolean matchRegex(Pattern pattern, String input) {
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	// ------------------------------------------------------------------------------------
	private boolean matchPasswordRegex(Pattern pattern, CharBuffer isMeetsPasswordRequirements) {
		Matcher matcher = pattern.matcher(isMeetsPasswordRequirements);
		return matcher.matches();
	}
	
	// ------------------------------------------------------------------------------------
	public boolean noEntryFound(String inputMeetsPasswordRequirements) {
		
		// If the password requirements are not met, print a message and then give specific feedback
		return matchRegex(NO_ENTRY_FOUND_PATTERN, inputMeetsPasswordRequirements);
	}
	
	// ------------------------------------------------------------------------------------
	public boolean validatePassword(char[] inputMeetsPasswordRequirements) {
		CharBuffer inputBuffer = CharBuffer.wrap(inputMeetsPasswordRequirements);
		
		// If the password requirements are not met, print a message and then give specific feedback
		return matchPasswordRegex(PASSWORD_REQUIREMENTS_PATTERN, inputBuffer);
	}
	
	// ------------------------------------------------------------------------------------
	public boolean containsLegalCharacters(String inputLegalCharacters) {
		// Return true or false (dependent on if the regex matches)
		return matchRegex(LEGAL_CHARACTERS_PATTERN, inputLegalCharacters);
	}

	// ------------------------------------------------------------------------------------
	// Check so see if input entered by the user is legal. If all characters
	// Entered are legal, then it will be return true. Otherwise, returns false.
	public boolean isOnlyLettersAndNumbers(String inputLettersAndNumbers) {
		
		return matchRegex(ONLY_LETTERS_AND_NUMBERS_PATTERN, inputLettersAndNumbers);
	}

	// ------------------------------------------------------------------------------------
	// Check so see if input entered by the user is legal. If all characters
	// Entered are legal, then it will be return true. Otherwise, returns false.
	public boolean isOnlyLetterCharacters(String inputOnlyLetters) {
		
		return matchRegex(ONLY_LETTER_CHARACTERS_PATTERN, inputOnlyLetters);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoUpperCaseCharacters(String inputNoUpperCase) {
		
		return matchRegex(NO_UPPERCASE_CHARACTERS_PATTERN, inputNoUpperCase);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoLowerCaseCharacters(String inputNoLowerCase) {
		
		return matchRegex(NO_LOWERCASE_CHARACTERS_PATTERN, inputNoLowerCase);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoSpecialCharacters(String inputNoSpecialCharacters) {
		
		return matchRegex(NO_SPECIAL_CHARACTERS_PATTERN, inputNoSpecialCharacters);
	}

	// ------------------------------------------------------------------------------------
	public boolean isNoNumbersFound(String inputNoNumbersFound) {
		return matchRegex(NO_NUMBERS_PATTERN, inputNoNumbersFound);
	}
	
	/*
	// -----------------------------------------------------------------------------------
	public String specificPasswordFeedback(String passwordInput) {
		StringBuilder specificMessage = new StringBuilder();

		// Create an array that will hold specific error messages
		// Such as no lower case, no upper case, no characters, and no numbers.
		String[] checks = {
				this.isNoLowerCaseCharacters(passwordInput),
				this.isNoUpperCaseCharacters(passwordInput),
				this.isNoSpecialCharacters(passwordInput),
				this.isNoNumbersFound(passwordInput)
		};
		
		boolean isNoEntryFound = this.noEntryFound(passwordInput).isEmpty();
		boolean isLegalCharacters = this.containsLegalCharacters(passwordInput).isEmpty();
		
		if (isNoEntryFound) { return "Field Empty: Must Enter Input"; }
		if (!isLegalCharacters) { return "Field Contains Illegal Characters"
				+ "\nFields can only contain characters ^@$!%*#?&"; }

		// Iterate through every element of 2D Array. If the message is not empty,
		// Then append the message to the current messages.
		for (int counter = 0; counter < checks.length; counter++) {
			if(!checks[counter].isEmpty()) {
				specificMessage.append(String.format("%s%n", checks[counter]));
			}
		}

		return specificMessage.toString();
	}
	*/
}
