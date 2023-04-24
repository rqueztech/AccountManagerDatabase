package com.rqueztech.swingworkers.configuration;

import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;
import com.rqueztech.models.admin.AdminModel;

public class SetupConfigurationWorker extends SwingWorker<Boolean, Void> {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;

	private char[] newAdminPassword;
	private char[] newAdminPassphrase;

	private byte[] encryptedAdminPassword;
	private byte[] encryptedAdminPassphrase;

	private byte[] newAdminPasswordSalt;
	private byte[] newAdminPassphraseSalt;

	private int adminNumber;

	public SetupConfigurationWorker(
			String adminFirstName,
			String adminLastName,
			char[] newAdminPassword,
			char[] newAdminPassphrase) {

		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.newAdminPassword = newAdminPassword;
		this.newAdminPassphrase = newAdminPassphrase;
	}

	// ------------------------------------------------------------------------
	@Override
	protected Boolean doInBackground() throws Exception {
		// TODO Auto-generated method stub
		this.adminAccountName = createAdminAccountName();

		this.newAdminPasswordSalt = this.generateNewAdminPasswordSalt();
		this.encryptedAdminPassword = PasswordEncryption.hashPassword(this.newAdminPassword, this.newAdminPasswordSalt);

		this.newAdminPassphraseSalt = this.generateNewAdminPassphraseSalt();
		this.encryptedAdminPassphrase = PasswordEncryption.hashPassword(this.newAdminPassphrase, this.newAdminPassphraseSalt);

		AdminModel adminModel =
				new AdminModel(
					this.adminAccountName,
					this.adminFirstName,
					this.adminLastName,
					this.encryptedAdminPassword,
					this.newAdminPasswordSalt,
					this.adminNumber
				);

		return null;
	}

	// ------------------------------------------------------------------------
	private String createAdminAccountName() {
		// Get the first string of the account name.
		String firstAccountNameString = this.adminFirstName.substring(0, 1);
		String secondAccountNameString = "";

		int lastNameLength = this.adminLastName.length();

		if (lastNameLength >= 2 && lastNameLength < 4) {
			secondAccountNameString = this.adminLastName.substring(0,lastNameLength);
		}

		else {
			secondAccountNameString = this.adminLastName.substring(0,4);
		}

		return String.format("%s%s", firstAccountNameString, secondAccountNameString);
	}

	// ------------------------------------------------------------------------
	private byte[] generateNewAdminPasswordSalt() {
		return PasswordEncryption.generateSalt();
	}

	// ------------------------------------------------------------------------
	private byte[] generateNewAdminPassphraseSalt() {
		return PasswordEncryption.generateSalt();
	}

}
