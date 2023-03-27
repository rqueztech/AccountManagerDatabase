package com.rqueztech.models.admin;

import com.rqueztech.ui.validation.InputValidations;

public class AdministratorModel {
	private char[] adminAccountName;
	private char[] adminFirstName;
	private char[] adminLastName;
	private char[] adminPassword;
	private byte adminSalt;
	private int adminNumber;
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public AdministratorModel(
			char[] adminAccountName,
			char[] adminFirstName,
			char[] adminLastName,
			char[] adminPassword,
			byte adminSalt,
			int adminNumber) {
		
		super();
		this.setAdminName(adminAccountName);
		this.setAdminFirstName(adminFirstName);
		this.setAdminLastName(adminLastName);
		this.setAdminPassword(adminPassword);
		this.setAdminSalt(adminSalt);
		this.setAdminNumber(adminNumber);
		
		this.inputValidations = new InputValidations();
	}
	
	// --------------------------------------------------------------------------------------
	public char[] getAdminName() {
		return this.adminAccountName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminName(char[] adminAccountName) {
		this.adminAccountName = adminAccountName;
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
