package com.rqueztech.ui.events;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PasswordFieldListener implements DocumentListener {
	
	private JPasswordField passwordField;
	
	public PasswordFieldListener(JPasswordField passwordField) {
		this.passwordField = passwordField;
		
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		this.passwordField.getPassword();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		this.passwordField.getPassword();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
