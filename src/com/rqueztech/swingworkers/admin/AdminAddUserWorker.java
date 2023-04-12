package com.rqueztech.swingworkers.admin;

import java.util.Arrays;

import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;
import com.rqueztech.interfaces.admin.AdminModelViewAddUserInterface;
import com.rqueztech.models.user.UserModel;

public class AdminAddUserWorker extends SwingWorker<UserModel, Void>
	implements AdminModelViewAddUserInterface {
	
	// NOTE: Creation of the default user password is automatic. No User Password field
	// Gets passed here.
	private String userFirstName;
	private String userLastName;
	private String gender;
	
	// Varibles that will be created in this class.
	private String userAccountName;
	private byte[] newUserSalt;
	private char[] defaultUserPassword;
	
	// This is the current user's number
	private int userNumber;
	
	// Hash the password from all of these parameters
	private byte[] userNewHashedPassword;
	
	// --------------------------------------------------------------------------------------
	public AdminAddUserWorker(String userFirstName, String userLastName, String gender) {
		// Set the first name, last name, and gender (which are the only 3 that
		// are currently passed into the AddUserWorker.
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.gender = gender;
	}
	
	// --------------------------------------------------------------------------------------
	@Override
	protected UserModel doInBackground() throws Exception {
		// TODO Auto-generated method stub
		this.createUserAccountName();
		
		// Key to append: This character will append to the end of
		// The default password. This can be changed later. Later iterations
		// Of the program will set randomized default passwords for users.
		char defaultUserPasswordAppend = this.userFirstName.toUpperCase().charAt(0);
		
		// Call the function to set the default user password
		this.setUserDefaultPassword(defaultUserPasswordAppend);
		
		this.generateUserSalt();// Generate the salt that will be used in
								// Hashing the user's password
		
		this.hashUserPassword();// Hash the user's password. This will be the
								// Password stored in the final model.
		
		// Note: Please change in the future
		this.userNumber = 0;	// Current number is 0. Placeholder.
		
		// Finally: Create the new user model with all of the appropriate information
		return new UserModel(this.userAccountName,this.userFirstName,this.userLastName,this.
				userNewHashedPassword,this.gender,this.newUserSalt,this.userNumber);
		
	}
	
	// --------------------------------------------------------------------------------------
	// NOTE: This should not be default. It is best to make a randomized password.
	// For the sake of simplicity, we will make it default to a simple default. Please
	// Change in the future.
	private void setUserDefaultPassword(char userFirstNameLetter) {
		// Initial base for the default password goes here
		char[] defaultUserPassword = {'a','b','c', userFirstNameLetter};
		this.defaultUserPassword = defaultUserPassword;
	}
	
	// --------------------------------------------------------------------------------------
	private void createUserAccountName() {
		// Get the first string of the account name.
		String firstAccountNameString = this.userFirstName.substring(0, 1);
		String secondAccountNameString = "";
		
		int lastNameLength = this.userLastName.length();
		
		if(lastNameLength >= 2 && lastNameLength < 4) {
			secondAccountNameString = this.userLastName.substring(0,lastNameLength);
		}
		
		else {
			secondAccountNameString = this.userLastName.substring(0,4);
		}
		
		this.userAccountName = String.format("%s%s", firstAccountNameString, secondAccountNameString);
	}
	
	// --------------------------------------------------------------------------------------
	private void generateUserSalt() {
		// Generate a salt in byte[] format
		this.newUserSalt = PasswordEncryption.generateSalt();
	}
	
	// --------------------------------------------------------------------------------------
	private void hashUserPassword() {
		this.userNewHashedPassword = PasswordEncryption.hashPassword(this.defaultUserPassword, this.newUserSalt);
		this.clearUserPassword();
	}
	
	// --------------------------------------------------------------------------------------
	private void clearUserPassword() {
		Arrays.fill(this.defaultUserPassword, '\0');
	}
	
	// --------------------------------------------------------------------------------------
	private void increaseEmployeeNumber() {
		this.userNumber++;
	}
	
	// --------------------------------------------------------------------------------------
	private int getEmployeeNumber() {
		return this.userNumber;
	}

	@Override
	public UserModel getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserFirstName(String userFirstName) {
		// TODO Auto-generated method stub
		this.userFirstName = userFirstName;
	}

	@Override
	public void setUserLastName(String userLastName) {
		// TODO Auto-generated method stub
		this.userLastName = userLastName;
	}
	
	@Override
	public void setUserGender(String gender) {
		// TODO Auto-generated method stub
		this.gender = gender;
	}
}
