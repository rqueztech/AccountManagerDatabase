package com.rqueztech.ui;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class TogglePasswordVisibility {
	private final int PASSWORD_PLAIN_TEXT_ASCII = 0;
	private final int PASSWORD_ENCRYPTED_TEXT_ASCII = 8226;
	
	public void passwordToggler(JPasswordField passwordField) {
		char currentEchoChar = passwordField.getEchoChar();
	    if(currentEchoChar == (char) PASSWORD_ENCRYPTED_TEXT_ASCII) {
	        passwordField.setEchoChar((char) PASSWORD_PLAIN_TEXT_ASCII);
	    } else {
	        passwordField.setEchoChar((char) PASSWORD_ENCRYPTED_TEXT_ASCII);
	    }
	}
}
