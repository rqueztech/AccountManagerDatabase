package com.rqueztech.ui.user.models;

public class UserModel {
	private char[] userFirstName;
	private char[] userLastName;
	private char[] userPassphrase;
	private char[] userPassword;
	private byte userSalt;
	private int userNumber;
	
	public UserModel(char[] userFirstName, 
			char[] userLastName, char[] userPassphrase, 
			char[] userPassword, byte userSalt,
			int userNumber) {
		
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassphrase = userPassphrase;
		this.userPassword = userPassword;
		this.userSalt = userSalt;
		this.userNumber = userNumber;
	}
}
