package com.rqueztech.ui.user.models;

import com.rqueztech.ui.validation.InputValidations;

public class UserModel {
	private char[] userFirstName;
	private char[] userLastName;
	private char[] userPassword;
	private byte userSalt;
	private int userNumber;
	
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public UserModel(char[] userFirstName, char[] userLastName, char[] userPassword, byte userSalt, int userNumber) {
		this.inputValidations = new InputValidations();
		
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.userSalt = userSalt;
		this.userNumber = userNumber;
	}

	// --------------------------------------------------------------------------------------
	public char[] getUserFirstName() {
		return userFirstName;
	}

	// --------------------------------------------------------------------------------------
	public void setUserFirstName(char[] userFirstName) {
		if(this.inputValidations.isOnlyLetterCharacters(userFirstName)) {
			this.userFirstName = userFirstName;
		}
	}

	// --------------------------------------------------------------------------------------
	public char[] getUserLastName() {
		return userLastName;
	}

	// --------------------------------------------------------------------------------------
	public void setUserLastName(char[] userLastName) {
		if(this.inputValidations.isOnlyLetterCharacters(userLastName)) {
			this.userLastName = userLastName;
		}
	}

	// --------------------------------------------------------------------------------------
	public char[] getUserPassword() {
		return userPassword;
	}

	// --------------------------------------------------------------------------------------
	public void setUserPassword(char[] userPassword) {
		if(this.inputValidations.validatePassword(userPassword)) {
			this.userPassword = userPassword;
		}
	}

	// --------------------------------------------------------------------------------------
	public byte getUserSalt() {
		return userSalt;
	}

	// --------------------------------------------------------------------------------------
	public void setUserSalt(byte userSalt) {
		this.userSalt = userSalt;
	}

	// --------------------------------------------------------------------------------------
	public int getUserNumber() {
		return userNumber;
	}

	// --------------------------------------------------------------------------------------
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
}
