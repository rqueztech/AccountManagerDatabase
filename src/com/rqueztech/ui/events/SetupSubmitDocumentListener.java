package com.rqueztech.ui.events;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rqueztech.ui.validation.InputValidations;

public class SetupSubmitDocumentListener implements DocumentListener {
	private JButton adminButton;
	private JTextField firstName;
	private JTextField lastName;
	private JPasswordField passphraseField;
	private JPasswordField confirmPassphraseField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private InputValidations inputValidations;
	
	public SetupSubmitDocumentListener(JButton adminButton, JTextField firstName, 
			JTextField lastName, JPasswordField passphraseField, 
		JPasswordField confirmPassphraseField, JPasswordField passwordField, 
		JPasswordField confirmPasswordField) {
		
		this.inputValidations = new InputValidations();
		
		this.adminButton = adminButton;
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.passphraseField = passphraseField;
		this.confirmPassphraseField = confirmPassphraseField;
		this.passwordField = passwordField;
		this.confirmPasswordField = confirmPasswordField;
	}
	
	public boolean isNullCheckPass() {
		// Check to see that none of the passwords are equal to null. There
		// Is no point to proceed if so.
		if(this.firstName != null && this.lastName != null
		&& this.passphraseField != null && this.confirmPassphraseField != null
		&& this.passwordField != null && this.confirmPasswordField != null
		&& this.firstName.getText().length() > 0
		&& this.lastName.getText().length() > 0
		&& this.passphraseField.getPassword().length > 0
		&& this.confirmPassphraseField.getPassword().length > 0
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
		if(this.inputValidations.isOnlyLetterCharacters(this.firstName.getText().toCharArray())
		&& this.inputValidations.isOnlyLetterCharacters(this.lastName.getText().toCharArray())
		&& this.inputValidations.validatePassword(this.passphraseField.getPassword())
		&& this.inputValidations.validatePassword(this.confirmPassphraseField.getPassword())
		&& this.inputValidations.validatePassword(this.passwordField.getPassword())
		&& this.inputValidations.validatePassword(this.confirmPasswordField.getPassword())) {

			return true;
		}
		
		return false;
	}
	
	public boolean isPassphrasePasswordMatch() {
		if(Arrays.equals(passphraseField.getPassword(), confirmPassphraseField.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	// Toggle the opacity
	public void opacityToggleOn() {
		this.adminButton.setOpaque(true);
		this.adminButton.setEnabled(true);
	}
	
	public void opacityToggleOff() {
		this.adminButton.setOpaque(false);
		this.adminButton.setEnabled(false);
	}
	
	public boolean passwordsConflict() {
		if(Arrays.equals(this.passphraseField.getPassword(), this.passwordField.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(this.isNullCheckPass() && this.isPasswordsValid()
		&& this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
		&& !this.passwordsConflict()) {
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
		&& this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
		&& !this.passwordsConflict()) {
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
		&& this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
		&& !this.passwordsConflict()) {
			this.opacityToggleOn();
		}
		
		else {
			this.opacityToggleOff();
		}
	}
}
