package com.rqueztech.ui.admin.models;

public class AdministratorModel {
	private char[] adminFirstName;
	private char[] adminLastName;
	private char[] adminPassphrase;
	private char[] adminPassword;
	private byte adminSalt;
	private int adminNumber;
	
	public AdministratorModel(char[] adminFirstName, 
			char[] adminLastName, char[] adminPassphrase, 
			char[] adminPassword, byte adminSalt,
			int adminNumber) {
		
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.adminPassphrase = adminPassphrase;
		this.adminPassword = adminPassword;
		this.adminSalt = adminSalt;
		this.adminNumber = adminNumber;
	}
}
