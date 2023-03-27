package com.rqueztech.models.user;

import com.rqueztech.ui.validation.InputValidations;

public class UserModel {
	private char[] userAccountName;
	private char[] userFirstName;
	private char[] userLastName;
	private char[] userPassword;
	private String gender; 
	private byte userSalt;
	private int userNumber;
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public UserModel(
			char[] userAccountName,
			char[] userFirstName,
			char[] userLastName,
			char[] userPassword,
			String gender,
			byte userSalt,
			int userNumber) {
		
		super();
		this.setUserName(userAccountName);
		this.setUserFirstName(userFirstName);
		this.setUserLastName(userLastName);
		this.setUserPassword(userPassword);
		this.setUserSalt(userSalt);
		this.setUserNumber(userNumber);
		
		this.inputValidations = new InputValidations();
	}
	
	// --------------------------------------------------------------------------------------
	
	
	// --------------------------------------------------------------------------------------
	
	
	// --------------------------------------------------------------------------------------
	public char[] getUserName() {
		return this.userAccountName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setUserName(char[] userAccountName) {
		this.userAccountName = userAccountName;
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
