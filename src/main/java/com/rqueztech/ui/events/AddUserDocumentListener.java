package com.rqueztech.ui.events;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rqueztech.ui.validation.InputValidations;

public class AddUserDocumentListener implements DocumentListener {
	private JButton addUserButton;
	private JTextField firstName;
	private JTextField lastName;
	private JPasswordField passphraseField;
	private JComboBox<String> comboBox;
	private InputValidations inputValidations;

	// ------------------------------------------------------------------------
	public AddUserDocumentListener(JButton addUserButton, JTextField firstName,
			JTextField lastName, JPasswordField passphraseField,
			JComboBox<String> comboBox) {

		this.inputValidations = new InputValidations();

		this.addUserButton = addUserButton;
		this.comboBox = comboBox;

		this.firstName = firstName;
		this.lastName = lastName;
		this.passphraseField = passphraseField;

		this.comboBox.addActionListener(e -> {
			if (this.isNullCheckPass()
					&& this.isPasswordsValid()
					&& this.comboBox.getSelectedIndex() != 0) {
				this.opacityToggleOn();
			}

			else {
				this.opacityToggleOff();
			}
		});
	}

	// ------------------------------------------------------------------------
	public boolean isNullCheckPass() {
		// Check to see that none of the passwords are equal to null. There
		// Is no point to proceed if so.
		if (this.firstName != null && this.lastName != null
		&& this.passphraseField != null
		&& this.comboBox.getSelectedIndex() != 0
		&& this.firstName.getText().length() > 0
		&& this.lastName.getText().length() > 0
		&& this.passphraseField.getPassword().length > 0) {
			return true;
		}

		return false;
	}

	// ------------------------------------------------------------------------
	public boolean isPasswordsValid() {
		if (this.inputValidations.isOnlyLetterCharacters(this.firstName.getText().toCharArray())
		&& this.inputValidations.isOnlyLetterCharacters(this.lastName.getText().toCharArray())
		&& this.inputValidations.validatePassword(this.passphraseField.getPassword())
		&& this.comboBox.getSelectedIndex() != 0) {
			return true;
		}

		return false;
	}

	// Toggle the opacity
	// ------------------------------------------------------------------------
	private void opacityToggleOn() {
		this.addUserButton.setOpaque(true);
		this.addUserButton.setEnabled(true);
	}

	// ------------------------------------------------------------------------
	private void opacityToggleOff() {
		this.addUserButton.setOpaque(false);
		this.addUserButton.setEnabled(false);
	}

	// ------------------------------------------------------------------------
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (this.isNullCheckPass() && this.isPasswordsValid()) {
			this.opacityToggleOn();
		}

		else {
			this.opacityToggleOff();
		}

	}

	// ------------------------------------------------------------------------
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (this.isNullCheckPass() && this.isPasswordsValid()) {
			this.opacityToggleOn();
		}

		else {
			this.opacityToggleOff();
		}
	}

	// ------------------------------------------------------------------------
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (this.isNullCheckPass() && this.isPasswordsValid()) {
			this.opacityToggleOn();
		}

		else {
			this.opacityToggleOff();
		}
	}
}
