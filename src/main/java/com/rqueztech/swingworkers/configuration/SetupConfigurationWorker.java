package com.rqueztech.swingworkers.configuration;

import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;

public class SetupConfigurationWorker extends SwingWorker<Boolean, Void> {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;
	private char[] newAdminPassword;
	private char[] newAdminPassphrase;
	private byte[] newAdminPasswordSalt;
	private byte[] newAdminPassphraseSalt;
	private String gender;
	private int adminNumber;
	
	public SetupConfigurationWorker(
			String adminAccountName, 
			String adminFirstName, 
			String adminLastName, 
			char[] newAdminPassword,
			char[] newAdminPassphrase) {
		
		this.adminAccountName = adminAccountName;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.newAdminPassword = newAdminPassword;
		this.newAdminPassphrase = newAdminPassphrase;
		
		this.generateNewAdminPasswordSalt();
		this.generateNewAdminPasswordSalt();
	}
	
	// --------------------------------------------------------------------------------------
	private void generateNewAdminPasswordSalt() {
		this.newAdminPasswordSalt = PasswordEncryption.generateSalt();
	}
	
	// --------------------------------------------------------------------------------------
	private void generateNewAdminPassphraseSalt() {
		this.newAdminPassphraseSalt = PasswordEncryption.generateSalt();
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
