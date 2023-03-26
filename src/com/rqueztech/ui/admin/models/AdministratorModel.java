package com.rqueztech.ui.admin.models;

import com.rqueztech.ui.validation.InputValidations;

public class AdministratorModel {
	private char[] adminFirstName;
	private char[] adminLastName;
	private char[] adminPassword;
	private byte adminSalt;
	private int adminNumber;
	
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public AdministratorModel(char[] adminFirstName, char[] adminLastName, char[] adminPassphrase, char[] adminPassword,
			byte adminSalt, int adminNumber) {
		super();
		this.setAdminFirstName(adminFirstName);
		this.setAdminLastName(adminLastName);
		this.setAdminPassword(adminPassword);
		this.setAdminSalt(adminSalt);
		this.setAdminNumber(adminNumber);
		
		this.inputValidations = new InputValidations();
	}
	
	// --------------------------------------------------------------------------------------
	public char[] getAdminFirstName() {
		return adminFirstName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminFirstName(char[] adminFirstName) {
		if(this.inputValidations.isOnlyLetterCharacters(adminFirstName)) {
			this.adminFirstName = adminFirstName;
		}
	}
	
	// --------------------------------------------------------------------------------------
	public char[] getAdminLastName() {
		return adminLastName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminLastName(char[] adminLastName) {
		if(this.inputValidations.isOnlyLetterCharacters(adminFirstName)) {
			this.adminLastName = adminLastName;
		}
	}
	
	// --------------------------------------------------------------------------------------
	public char[] getAdminPassword() {
		return adminPassword;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminPassword(char[] adminPassword) {
		if(this.inputValidations.validatePassword(adminPassword)) {
			this.adminPassword = adminPassword;
		}
	}
	
	// --------------------------------------------------------------------------------------
	public byte getAdminSalt() {
		return adminSalt;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminSalt(byte adminSalt) {
		this.adminSalt = adminSalt;
	}
	
	// --------------------------------------------------------------------------------------
	public int getAdminNumber() {
		return adminNumber;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}	
}
