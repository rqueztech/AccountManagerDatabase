package com.rqueztech.models.user;

import com.rqueztech.ui.validation.InputValidations;

public class UserModel {
	private String userAccountName;
	private String userFirstName;
	private String userLastName;
	private char[] userPassword;
	private byte userSalt;
	private String gender; 
	private int userNumber;
	
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public UserModel(
			String userAccountName,
			String userFirstName,
			String userLastName,
			char[] userPassword,
			String gender,
			byte userSalt,
			int userNumber) {
		
		this.setUserName(userAccountName);
		this.setUserFirstName(userFirstName);
		this.setUserLastName(userLastName);
		this.setUserPassword(userPassword);
		this.setGender(gender);
		this.setUserSalt(userSalt);
		this.setUserNumber(userNumber);
		
		this.inputValidations = new InputValidations();
	}
	
	// --------------------------------------------------------------------------------------
	public String getGender() {
		return this.gender;
	}
	
	// --------------------------------------------------------------------------------------
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	// --------------------------------------------------------------------------------------
	public String getUserName() {
		return this.userAccountName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setUserName(String userAccountName) {
		this.userAccountName = userAccountName;
	}

	// --------------------------------------------------------------------------------------
	public String getUserFirstName() {
		return userFirstName;
	}

	// --------------------------------------------------------------------------------------
	public void setUserFirstName(String userFirstName) {
		if(this.inputValidations.isOnlyLetterCharacters(userFirstName)) {
			this.userFirstName = userFirstName;
		}
	}

	// --------------------------------------------------------------------------------------
	public String getUserLastName() {
		return userLastName;
	}

	// --------------------------------------------------------------------------------------
	public void setUserLastName(String userLastName) {
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
