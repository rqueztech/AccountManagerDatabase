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
import com.rqueztech.ui.user.enums.UserChangeDefaultPasswordEnums;

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
	private void resetFields() {

		for(Component component : this.userChangeDefaultPasswordPanel.getComponentsMap().values()) {
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
	private void submitButtonActionListener() {
		JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.SUBMIT_LOGIN_BUTTON_KEY);
		
		submitButton.addActionListener(e -> {
			this.userChangeDefaultPasswordPanel.setVisible(false);
			this.resetFields();
			this.userChangeDefaultPasswordPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void cancelButtonActionListener() {
		JButton cancelButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CANCEL_CHANGE_BUTTON_KEY);
		
		cancelButton.addActionListener(e -> {
			this.userChangeDefaultPasswordPanel.setVisible(false);
			this.resetFields();
			this.userChangeDefaultPasswordPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
        this.toggleConfirmPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	private void setListeners() {
		this.passwordListener();
		this.confirmPasswordListener();
		this.submitButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void submitButtonListener() {
		JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.SUBMIT_LOGIN_BUTTON_KEY);
		JPasswordField enterPassword = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_TEXTFIELD_KEY);
		JPasswordField confirmPassword = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
		
		ChangePasswordDocumentListener changePasswordDocumentListener
			= new ChangePasswordDocumentListener(submitButton, enterPassword, confirmPassword);
		
		enterPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
		confirmPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void passwordListener() {
		JButton passwordButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_VISIBILITY_BUTTON_KEY);
		JPasswordField passwordField = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_TEXTFIELD_KEY);
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passwordField, passwordButton);
		passwordField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void confirmPasswordListener() {
		JButton passphraseButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY);
		JPasswordField passphraseField = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passphraseField, passphraseButton);
		passphraseField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	void toggleConfirmPasswordVisibility() {
		JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField confirmPasswordTextField = (JPasswordField) this.userChangeDefaultPasswordPanel.getComponentsMap().get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(confirmPasswordTextField);
		});
	}
}
