package com.rqueztech.models.user;

import com.rqueztech.ui.validation.InputValidations;

public class UserModel {
	private String userAccountName;
	private String userFirstName;
	private String userLastName;
	private byte[] userHashedPassword;
	private byte[] userSalt;
	private String gender;
	private int userNumber;

	private InputValidations inputValidations;

	// ------------------------------------------------------------------------
	public UserModel(
			String userAccountName,
			String userFirstName,
			String userLastName,
			byte[] userHashedPassword,
			String gender,
			byte[] userSalt,
			int userNumber) {

		System.out.println("HERE");

		this.inputValidations = new InputValidations();

		this.setUserName(userAccountName);
		this.setUserFirstName(userFirstName);
		this.setUserLastName(userLastName);
		this.setUserPassword(userHashedPassword);
		this.setGender(gender);
		this.setUserSalt(userSalt);
		this.setUserNumber(userNumber);
	}

	// ------------------------------------------------------------------------
	public String getGender() {
		return this.gender;
	}

	// ------------------------------------------------------------------------
	public void setGender(String gender) {
		this.gender = gender;
	}

	// ------------------------------------------------------------------------
	public String getUserName() {
		return this.userAccountName;
	}

	// ------------------------------------------------------------------------
	public void setUserName(String userAccountName) {
		this.userAccountName = userAccountName;
	}

	// ------------------------------------------------------------------------
	public String getUserFirstName() {
		return userFirstName;
	}

	// ------------------------------------------------------------------------
	public void setUserFirstName(String userFirstName) {
		if (this.inputValidations.isOnlyLetterCharacters(userFirstName)) {
			this.userFirstName = userFirstName;
		}
	}

	// ------------------------------------------------------------------------
	public String getUserLastName() {
		return userLastName;
	}

	// ------------------------------------------------------------------------
	public void setUserLastName(String userLastName) {
		if (this.inputValidations.isOnlyLetterCharacters(userLastName)) {
			this.userLastName = userLastName;
		}
	}

	// ------------------------------------------------------------------------
	public byte[] getUserPassword() {
		return userHashedPassword;
	}

	// ------------------------------------------------------------------------
	public void setUserPassword(byte[] userHashedPassword) {
		this.userHashedPassword = userHashedPassword;
	}

	// ------------------------------------------------------------------------
	public byte[] getUserSalt() {
		return this.userSalt;
	}

	// ------------------------------------------------------------------------
	public void setUserSalt(byte[] userSalt) {
		this.userSalt = userSalt;
	}

	// ------------------------------------------------------------------------
	public int getUserNumber() {
		return userNumber;
	}

	// ------------------------------------------------------------------------
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
}
