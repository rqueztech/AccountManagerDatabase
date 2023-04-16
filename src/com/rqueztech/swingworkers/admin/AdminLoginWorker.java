package com.rqueztech.swingworkers.admin;

import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;

public class AdminLoginWorker extends SwingWorker<Boolean, Void> {

	private PasswordEncryption passwordEncryption;
	
	private String adminName;
	private char[] enteredPassword;
	private byte storedAdminSalt;
	
	public AdminLoginWorker(String adminName, char[] enteredPassword, char[] enteredPassphrase) {
		this.passwordEncryption = new PasswordEncryption();
		this.adminName = adminName;
		this.enteredPassword = enteredPassword;
	}
	
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
				JOptionPane.showMessageDialog(null, "AdminAddUserWorker");
			} else {
				JOptionPane.showMessageDialog(null, "AdminLoginWorker FAIL");
			}
		} catch (InterruptedException | ExecutionException e) {
			JOptionPane.showMessageDialog(null, "Error searching error.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
