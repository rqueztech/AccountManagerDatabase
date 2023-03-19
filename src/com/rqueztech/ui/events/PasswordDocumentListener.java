package com.rqueztech.ui.events;

import java.awt.Color;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rqueztech.ui.validation.InputValidations;

public class PasswordDocumentListener implements DocumentListener {
	
	private JPasswordField passwordField;
	private JButton currentButton;
	private InputValidations inputValidations;
	
	// Represents 1 in binary
	private final int HAS_UPPERCASE = 1;
	
	// Push one to the left once, 10 in binary
	private final int HAS_LOWERCASE = 1 << 1;
	
	// Push one to the left once, 100 in binary
	private final int HAS_SYMBOL = 1 << 2;
	
	// Push one to the left once, 1000 in binary
	private final int HAS_NUMBER = 1 << 3;
	
	// Push one to the left once, 10000 in binary
	private final int HAS_ILLEGAL = 1 << 4;
	
	// Push one to the left once, 10000 in binary
	private final int HAS_MINCHARACTERS = 1 << 5;
	
	private StringBuilder sb;
	private ConcurrentHashMap <Integer, String> stringMessage; 
	private boolean meetsPasswordRequirements = false;
	
	public PasswordDocumentListener(
			JPasswordField passwordField,
			JButton currentButton) 
	{
		this.sb = new StringBuilder();
		
		this.stringMessage = new ConcurrentHashMap<Integer, String>();
		
		this.inputValidations = new InputValidations();
		this.passwordField = passwordField;
		this.currentButton = currentButton;
		
		this.stringMessage.put(HAS_MINCHARACTERS, "8 Characters");
		this.stringMessage.put(HAS_UPPERCASE, "1 Uppercase");
		this.stringMessage.put(HAS_LOWERCASE, "1 Lowercase");
		this.stringMessage.put(HAS_SYMBOL, "1 Symbol");
		this.stringMessage.put(HAS_NUMBER, "1 Number");
		this.stringMessage.put(HAS_ILLEGAL, "No Illegal");
		
		this.updateToolTip();
	}
	
	public void updateButton() {
		if(this.inputValidations.validatePassword(this.passwordField.getPassword())) {
			this.currentButton.setBackground(Color.GREEN);
			this.passwordField.setToolTipText("Meets Requirements");
		}
		
		else {
			this.currentButton.setBackground(Color.RED);
		}
		
		this.updateToolTip();
	}
	
	public void updateToolTip() {
		int flags = 0;
		
		if(this.passwordField.getPassword().length < 8) {
			flags |= HAS_MINCHARACTERS;
		}
		
		if(!this.inputValidations.containsLegalCharacters(this.passwordField.getPassword())) {
			flags |= HAS_ILLEGAL;
		}
		
		if(this.inputValidations.isNoUpperCaseCharacters(this.passwordField.getPassword())) {
			flags |= HAS_UPPERCASE;
		}
		
		if(this.inputValidations.isNoLowerCaseCharacters(this.passwordField.getPassword())) {
			flags |= HAS_LOWERCASE;
		}
		
		if(this.inputValidations.isNoSpecialCharacters(this.passwordField.getPassword())) {
			flags |= HAS_SYMBOL;
		}
		
		if(this.inputValidations.isNoNumbersFound(this.passwordField.getPassword())) {
			flags |= HAS_NUMBER;
		}
		
		sb = new StringBuilder();
	    for (Integer flag : this.stringMessage.keySet()) {
	        if ((flags & flag) == flag) {
	            sb.append(this.stringMessage.get(flag)).append(" ");
	        }
	    }
    
	    if(sb.toString().equals("")) {
	    	sb.setLength(0);
	    	sb.append("Meets Requirements");
	    }
		
	    passwordField.setToolTipText(sb.toString());
	}
	
	public void resetButton() {
		if(this.passwordField.getPassword().length == 0) {
			this.currentButton.setBackground(Color.BLACK);
			this.sb.setLength(0);
			this.sb.append("Field Must Not Be Blank");
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
