package com.rqueztech.ui.events;

import javax.swing.JPasswordField;

public class TogglePasswordVisibility {
	private final int PASSWORD_PLAIN_TEXT_ASCII = 0;
	private final int PASSWORD_ENCRYPTED_TEXT_ASCII = 8226;
	
	// Function will get the passwordField passed into it
	public void passwordToggler(JPasswordField passwordField) {
		
		// Get the current character representing input from the user
		char currentEchoChar = passwordField.getEchoChar();
		
		// TURN ON VISIBILITY: If the input is encrypted characters, convert it to plain text
	    if(currentEchoChar == (char) PASSWORD_ENCRYPTED_TEXT_ASCII) {
	        passwordField.setEchoChar((char) PASSWORD_PLAIN_TEXT_ASCII);
	    } 
	    
	    // TURN OFF VISIBILITY: If the input is plaintext, encrypt it again
	    else {
	        passwordField.setEchoChar((char) PASSWORD_ENCRYPTED_TEXT_ASCII);
	    }
	}
}
