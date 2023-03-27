package com.rqueztech.controllers.user;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.ChangePasswordDocumentListener;
import com.rqueztech.ui.events.PasswordValidationDocumentListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;
import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

public class UserChangeDefaultPasswordController {
	private UserChangeDefaultPasswordPanel userChangeDefaultPasswordPanel;
	private TogglePasswordVisibility togglePasswordVisibility;
	
	public UserChangeDefaultPasswordController(UserChangeDefaultPasswordPanel userChangeDefaultPasswordPanel) {
		this.userChangeDefaultPasswordPanel = userChangeDefaultPasswordPanel;
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.submitButtonActionListener();
        this.enablePasswordTogglers();
        this.setListeners();
        this.submitButtonListener();
        this.cancelButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void resetFields() {

		for(Component component : this.userChangeDefaultPasswordPanel.components().values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}
	
	// --------------------------------------------------------------------------------------
	public void submitButtonActionListener() {
		JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("SUBMIT_LOGIN_BUTTON_KEY");
		
		submitButton.addActionListener(e -> {
			this.userChangeDefaultPasswordPanel.setVisible(false);
			this.resetFields();
			this.userChangeDefaultPasswordPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void cancelButtonActionListener() {
		JButton cancelButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("CANCEL_CHANGE_BUTTON_KEY");
		
		cancelButton.addActionListener(e -> {
			this.userChangeDefaultPasswordPanel.setVisible(false);
			this.resetFields();
			this.userChangeDefaultPasswordPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
        this.toggleConfirmPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void setListeners() {
		this.passwordListener();
		this.confirmPasswordListener();
		this.submitButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void submitButtonListener() {
		JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("SUBMIT_LOGIN_BUTTON_KEY");
		JPasswordField enterPassword = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("ENTERPASSWORD_TEXTFIELD_KEY");
		JPasswordField confirmPassword = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY");
		
		ChangePasswordDocumentListener changePasswordDocumentListener
			= new ChangePasswordDocumentListener(submitButton, enterPassword, confirmPassword);
		
		enterPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
		confirmPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void passwordListener() {
		JButton passwordButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("ENTERPASSWORD_VISIBILITY_BUTTON_KEY");
		JPasswordField passwordField = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("ENTERPASSWORD_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passwordField, passwordButton);
		passwordField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void confirmPasswordListener() {
		JButton passphraseButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY");
		JPasswordField passphraseField = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passphraseField, passphraseButton);
		passphraseField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("ENTERPASSWORD_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("ENTERPASSWORD_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleConfirmPasswordVisibility() {
		JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel.components().get("CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField confirmPasswordTextField = (JPasswordField) this.userChangeDefaultPasswordPanel.components().get("CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(confirmPasswordTextField);
		});
	}
}
