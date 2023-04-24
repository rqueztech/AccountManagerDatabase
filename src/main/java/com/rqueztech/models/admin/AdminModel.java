package com.rqueztech.models.admin;

public class AdminModel {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;
	private byte[] adminPassword;
	private byte[] adminSalt;
	private int adminNumber;

	// ------------------------------------------------------------------------
	public AdminModel(
			String adminAccountName,
			String adminFirstName,
			String adminLastName,
			byte[] adminPassword,
			byte[] adminSalt,
			int adminNumber) {

		this.setAdminAccountNameName(adminAccountName);
		this.setAdminFirstName(adminFirstName);
		this.setAdminLastName(adminLastName);
		this.setAdminPassword(adminPassword);
		this.setAdminSalt(adminSalt);
		this.setAdminNumber(adminNumber);
	}

	// ------------------------------------------------------------------------
	public String getAdminName() {
		return this.adminAccountName;
	}

	// ------------------------------------------------------------------------
	public void setAdminAccountNameName(String adminAccountName) {
		this.adminAccountName = adminAccountName;
	}

	// ------------------------------------------------------------------------
	public String getAdminFirstName() {
		return adminFirstName;
	}

	// ------------------------------------------------------------------------
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	// ------------------------------------------------------------------------
	public String getAdminLastName() {
		return adminLastName;
	}

	// ------------------------------------------------------------------------
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}

	// ------------------------------------------------------------------------
	public byte[] getAdminPassword() {
		return adminPassword;
	}

	// ------------------------------------------------------------------------
	public void setAdminPassword(byte[] adminPassword) {
		this.adminPassword = adminPassword;
	}

	// ------------------------------------------------------------------------
	public byte[] getAdminSalt() {
		return adminSalt;
	}

	// ------------------------------------------------------------------------
	public void setAdminSalt(byte[] adminSalt) {
		this.adminSalt = adminSalt;
	}

	// ------------------------------------------------------------------------
	public int getAdminNumber() {
		return adminNumber;
	}

	// ------------------------------------------------------------------------
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}	
}
