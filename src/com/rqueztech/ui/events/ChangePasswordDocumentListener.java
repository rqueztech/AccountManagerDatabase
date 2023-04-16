package com.rqueztech.ui.events;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rqueztech.ui.validation.InputValidations;

public class ChangePasswordDocumentListener implements DocumentListener {
	private JButton adminButton;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private InputValidations inputValidations;
	
	public ChangePasswordDocumentListener(
			JButton adminButton, 
			JPasswordField passwordField, 
			JPasswordField confirmPasswordField) {
		
		this.inputValidations = new InputValidations();
		this.adminButton = adminButton;
		this.passwordField = passwordField;
		this.confirmPasswordField = confirmPasswordField;
		
	}
	
	public boolean isNullCheckPass() {
		// Check to see that none of the passwords are equal to null. There
		// Is no point to proceed if so.
		if(this.passwordField != null && this.confirmPasswordField != null
		&& this.passwordField.getPassword().length > 0
		&& this.confirmPasswordField.getPassword().length > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isPasswordsMatch() {
		// If the password fields match, return true. Passwords do match
		if (Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {		
			return true;
		}
		
		// If the password fields don't match, return false
		return false;
	}
	
	public boolean isPasswordsValid() {
		if(this.inputValidations.validatePassword(this.passwordField.getPassword())
		&& this.inputValidations.validatePassword(this.confirmPasswordField.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	public boolean isPasswordMatch() {
		if(Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	// Toggle the opacity
	private void opacityToggleOn() {
		this.adminButton.setOpaque(true);
		this.adminButton.setEnabled(true);
	}
	
	private void opacityToggleOff() {
		this.adminButton.setOpaque(false);
		this.adminButton.setEnabled(false);
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(this.isNullCheckPass() && this.isPasswordsValid()
		&& this.isPasswordsMatch()) {
			this.opacityToggleOn();
		}
		
		else {
			this.opacityToggleOff();
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(this.isNullCheckPass() && this.isPasswordsValid()
		&& this.isPasswordsMatch()) {
			this.opacityToggleOn();
		}
		
		else {
			this.opacityToggleOff();
		}
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(this.isNullCheckPass() && this.isPasswordsValid()
		&& this.isPasswordsMatch()) {
			this.opacityToggleOn();
		}
		
		else {
			this.opacityToggleOff();
		}
	}
}
