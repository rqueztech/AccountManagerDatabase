package com.rqueztech.models.admin;

import com.rqueztech.ui.validation.InputValidations;

public class AdminModel {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;
	private char[] adminPassword;
	private byte adminSalt;
	private int adminNumber;
	
	private InputValidations inputValidations;
	
	// --------------------------------------------------------------------------------------
	public AdminModel(
			String adminAccountName,
			String adminFirstName,
			String adminLastName,
			char[] adminPassword,
			byte adminSalt,
			int adminNumber) {
		
		this.setAdminName(adminAccountName);
		this.setAdminFirstName(adminFirstName);
		this.setAdminLastName(adminLastName);
		this.setAdminPassword(adminPassword);
		this.setAdminSalt(adminSalt);
		this.setAdminNumber(adminNumber);
		
		this.inputValidations = new InputValidations();
	}
	
	// --------------------------------------------------------------------------------------
	public String getAdminName() {
		return this.adminAccountName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminName(String adminAccountName) {
		this.adminAccountName = adminAccountName;
	}
	
	// --------------------------------------------------------------------------------------
	public String getAdminFirstName() {
		return adminFirstName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminFirstName(String adminFirstName) {
		if(this.inputValidations.isOnlyLetterCharacters(adminFirstName)) {
			this.adminFirstName = adminFirstName;
		}
	}
	
	// --------------------------------------------------------------------------------------
	public String getAdminLastName() {
		return adminLastName;
	}
	
	// --------------------------------------------------------------------------------------
	public void setAdminLastName(String adminLastName) {
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
