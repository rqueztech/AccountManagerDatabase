package com.rqueztech.swingworkers.admin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.rqueztech.encryption.PasswordEncryption;
import com.rqueztech.ui.admin.enums.AdminAddUserEnums;

public class AdminAddUserWorker extends SwingWorker<Boolean, Void> {
	private PasswordEncryption passwordEncryption;
	
	// NOTE: Creation of the default user password is automatic. No User Password field
	// Gets passed here.
	private String userFirstName;
	private String userLastName;
	private byte[] newUserSalt;
	private String gender;
	private int userNumber;
	
	
	// GENERATE:
	// Username/Password will be set in this funciton
	
	// --------------------------------------------------------------------------------------
	public AdminAddUserWorker(String userFirstName, String userLastName, String gender) {
		this.passwordEncryption = new PasswordEncryption();
		
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.gender = gender;
		
		this.generateNewUserSalt();
	}
	
	// --------------------------------------------------------------------------------------
	private void generateNewUserSalt() {
		this.newUserSalt = passwordEncryption.generateSalt();
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
				String message = this.userFirstName + " " + this.userLastName + " " + gender;
				
				System.out.println(this.userFirstName + this.userLastName + gender);
				System.out.println(newUserSalt);
				
				JOptionPane.showMessageDialog(null, message);
			} else {
				JOptionPane.showMessageDialog(null, "AdminAddUserWorker FAIL");
			}
		} catch (InterruptedException | ExecutionException e) {
			JOptionPane.showMessageDialog(null, "Error searching error.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
