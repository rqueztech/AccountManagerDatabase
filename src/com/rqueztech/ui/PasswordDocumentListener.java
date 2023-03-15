package com.rqueztech.ui;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;
import com.rqueztech.ui.validation.InputValidations;

public class PasswordDocumentListener implements DocumentListener {
	
	private JPasswordField passwordField;
	private JButton currentButton;
	private InputValidations inputValidations;
	
	public PasswordDocumentListener(
			JPasswordField passwordField,
			JButton currentButton) 
	{
		this.inputValidations = new InputValidations();
		this.passwordField = passwordField;
		this.currentButton = currentButton;
	}

	public void updateButton() {
		if(this.inputValidations.validatePassword(this.passwordField.getPassword())) {
			this.currentButton.setBackground(Color.GREEN);
		}
		
		else {
			this.currentButton.setBackground(Color.RED);
		}
	}
	
	public void resetButton() {
		if(this.passwordField.getPassword().length == 0) {
			this.currentButton.setBackground(Color.BLACK);
		}
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateButton();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		resetButton();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
