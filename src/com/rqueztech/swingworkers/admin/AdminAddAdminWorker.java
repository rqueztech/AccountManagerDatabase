package com.rqueztech.swingworkers.admin;

import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;

public class AdminAddAdminWorker extends SwingWorker<Boolean, Void> {
	private String adminAccountName;
	private String adminFirstName;
	private String adminLastName;
	private char[] newAdminPassword;
	private byte[] newAdminSalt;
	private String gender;
	private int adminNumber;
	
	private PasswordEncryption passwordEncryption;
	
	// --------------------------------------------------------------------------------------
	public AdminAddAdminWorker(String adminAccountName, String adminFirstName, String adminLastName, char[] newAdminPassword, String gender, int adminNumber) {
		this.passwordEncryption = new PasswordEncryption();
		
		this.adminAccountName = adminAccountName;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.newAdminPassword = newAdminPassword;
		this.generateNewAdminSalt();
		this.gender = gender;
		this.adminNumber = adminNumber;
	}
	
	// --------------------------------------------------------------------------------------
	private void generateNewAdminSalt() {
		this.newAdminSalt = passwordEncryption.generateSalt();
	}
	
	// --------------------------------------------------------------------------------------
	@Override
	protected Boolean doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		return true;
	}

	
	@Override
	protected void done() {
		try {
			boolean success = get();

			if (success) {
				String dataPassed = String
						.format("ISTHISTHEONE?%s, %s, %s, ",
								this.adminAccountName,
								this.adminFirstName,
								this.adminLastName);
				
				//JOptionPane.showMessageDialog(null, dataPassed);
			} else {
				JOptionPane.showMessageDialog(null, "AdminAddAdminWorker FAIL");
			}
		} catch (InterruptedException | ExecutionException e) {
			JOptionPane.showMessageDialog(null, "Error searching error.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
