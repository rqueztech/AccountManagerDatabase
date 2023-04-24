package com.rqueztech.swingworkers.admin;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;
import com.rqueztech.models.admin.AdminModel;

public class AdminAddAdminWorker extends SwingWorker<AdminModel, Void> {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;

	private char[] newAdminPassword;
	private byte[] encryptedAdminPassword;

	private byte[] newAdminPasswordSalt;

	private int adminNumber;

	public AdminAddAdminWorker(
			String adminFirstName,
			String adminLastName,
			char[] newAdminPassword,
			char[] newAdminPassphrase) {

		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.newAdminPassword = newAdminPassword;
	}

	// ------------------------------------------------------------------------
	@Override
	protected AdminModel doInBackground() throws Exception {
		System.out.println("EXECUTION!!!");

		// TODO Auto-generated method stub
		this.adminAccountName = createAdminAccountName();

		this.newAdminPasswordSalt = this.generateNewAdminPasswordSalt(); // Create the salt that will be used for the admin password

		this.encryptedAdminPassword = PasswordEncryption	// Create the hashed encrypted password
				.hashPassword(this.newAdminPassword,
						this.newAdminPasswordSalt);

		return new AdminModel(
			this.adminAccountName,
			this.adminFirstName,
			this.adminLastName,
			this.encryptedAdminPassword,
			this.newAdminPasswordSalt,
			this.adminNumber
		);
	}

	// ------------------------------------------------------------------------
	@Override
	protected void done() {
	    try {
	        // Get the result of the SwingWorker's background task
	        AdminModel result = get();

	        // Handle the successful completion of the task here
	        String message = "Admin added successfully.\n"
	        		+ result.getAdminName() + "\n"
	        		+ result.getAdminFirstName() + "\n"
	        		+ result.getAdminLastName() + "\n"
	        		+ result.getAdminPassword() + "\n"
	        		+ result.getAdminSalt() + "\n"
	        		+ result.getAdminNumber() + "\n"; // End String message

	        JOptionPane.showMessageDialog(null, message);

	    } catch (InterruptedException | ExecutionException ex) {
	        // Handle any exceptions that were thrown during the background task here
	        String errorMessage = "Error: " + ex.getMessage();
	        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    } catch (CancellationException ex) {
	        // Handle cancellation of the background task here
	        String message = "The task was cancelled.";
	        JOptionPane.showMessageDialog(null, message);
	    }
	}

	// ------------------------------------------------------------------------
	private String createAdminAccountName() {
		// Get the first string of the account name.
		String firstAccountNameString = this.adminFirstName.substring(0, 1);
		String secondAccountNameString = "";

		int lastNameLength = this.adminLastName.length();

		if (lastNameLength < 4) {
			secondAccountNameString = this.adminLastName;
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

}
