package com.rqueztech.ui.configuration;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.PasswordValidationDocumentListener;
import com.rqueztech.ui.events.SetupSubmitDocumentListener;
import com.rqueztech.ui.events.TextFieldListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class SetupConfigurationController {
	private SetupConfigurationPanel setupConfigurationPanel;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	public SetupConfigurationController(SetupConfigurationPanel setupConfigurationPanel) {
		this.setupConfigurationPanel = setupConfigurationPanel;
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.enableDocumentListeners();
        this.enableTogglers();
        
        this.submitActionListener();
        this.exitActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void exitActionListener() {
		JButton adminLogin = (JButton) this.setupConfigurationPanel.getComponentsMap().get("EXIT_BUTTON_KEY");
		
		adminLogin.addActionListener(e -> {
			this.setupConfigurationPanel.setVisible(false);
			this.resetFields();
			this.setupConfigurationPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.SETUP_AGREEMENT_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void enableTogglers() {
		this.togglePassphraseVisibility();
		this.togglePasswordVisibility();
		this.toggleConfirmPasswordVisibility();
		this.toggleConfirmPassphraseVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	private void resetFields() {

		for(Component component : this.setupConfigurationPanel.getComponentsMap().values()) {
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
	private void toggleConfirmPassphraseVisibility() {
		JButton toggleButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField confirmPassphraseTextField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSPHRASE_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(confirmPassphraseTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void togglePasswordVisibility(){
		JButton toggleButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("PASSWORD_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField passphraseTextField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSWORD_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(passphraseTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void toggleConfirmPasswordVisibility(){
		JButton toggleButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField passphraseTextField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSWORD_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(passphraseTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void togglePassphraseVisibility() {
		JButton toggleButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("PASSPHRASE_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField passphraseTextField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(passphraseTextField);
		});
	}
	
	private void enableDocumentListeners() {
		this.setValidPassphraseFeedback();
		this.setValidConfirmPassphraseFeedback();
		
		this.setValidPasswordFeedback();
		this.setValidConfirmPasswordFeedback();
		this.setSubmitButtonFeedback();
		this.submitActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void submitActionListener() {
		JButton adminLogin = (JButton) this.setupConfigurationPanel.getComponentsMap().get("SUBMIT_BUTTON_KEY");
		
		adminLogin.addActionListener(e -> {
			this.setupConfigurationPanel.setVisible(false);
			this.resetFields();
			this.setupConfigurationPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void setSubmitButtonFeedback() {
		JButton adminSubmitButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("SUBMIT_BUTTON_KEY");
		
		JTextField firstName = (JTextField) this.setupConfigurationPanel.getComponentsMap().get("FIRSTNAME_TEXTFIELD_KEY");
		JTextField lastName = (JTextField) this.setupConfigurationPanel.getComponentsMap().get("LASTNAME_TEXTFIELD_KEY");
		
		JPasswordField passphraseField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
		JPasswordField confirmPassphraseField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSPHRASE_TEXTFIELD_KEY");
		JPasswordField passwordField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSWORD_TEXTFIELD_KEY");
		JPasswordField confirmPasswordField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSWORD_TEXTFIELD_KEY");
		
		// Create a listener for the first name field
		TextFieldListener nameFieldListener = 
				new TextFieldListener(firstName);
		
		// Listener for the last name field
		TextFieldListener lastNameFieldListener = 
				new TextFieldListener(lastName);
		
		firstName.getDocument().addDocumentListener(nameFieldListener);
		lastName.getDocument().addDocumentListener(lastNameFieldListener);
		
		SetupSubmitDocumentListener submitDocumentListener = 
				new SetupSubmitDocumentListener(adminSubmitButton, firstName, lastName,
				passphraseField, confirmPassphraseField, passwordField, confirmPasswordField);
		
		firstName.getDocument().addDocumentListener(submitDocumentListener);
		lastName.getDocument().addDocumentListener(submitDocumentListener);
		passphraseField.getDocument().addDocumentListener(submitDocumentListener);
		confirmPassphraseField.getDocument().addDocumentListener(submitDocumentListener);
		passwordField.getDocument().addDocumentListener(submitDocumentListener);
		confirmPasswordField.getDocument().addDocumentListener(submitDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void setValidPassphraseFeedback() {
		JButton passphraseButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("PASSPHRASE_VISIBILITY_BUTTON_KEY");
		JPasswordField passphraseField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener passphraseDocumentListener = new PasswordValidationDocumentListener(passphraseField, passphraseButton);
		passphraseField.getDocument().addDocumentListener(passphraseDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void setValidConfirmPassphraseFeedback() {
		JButton confirmPassphraseButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY");
		JPasswordField confirmPassphraseField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSPHRASE_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener confirmPassphraseDocumentListener = new PasswordValidationDocumentListener(confirmPassphraseField, confirmPassphraseButton);
		confirmPassphraseField.getDocument().addDocumentListener(confirmPassphraseDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void setValidPasswordFeedback() {
		JButton passwordButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("PASSWORD_VISIBILITY_BUTTON_KEY");
		JPasswordField passwordField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("PASSWORD_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passwordField, passwordButton);
		passwordField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void setValidConfirmPasswordFeedback() {
		JButton confirmPasswordButton = (JButton) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY");
		JPasswordField confirmPasswordField = (JPasswordField) this.setupConfigurationPanel.getComponentsMap().get("CONFIRM_PASSWORD_TEXTFIELD_KEY");
		
		PasswordValidationDocumentListener confirmPasswordDocumentListener = new PasswordValidationDocumentListener(confirmPasswordField, confirmPasswordButton);
		confirmPasswordField.getDocument().addDocumentListener(confirmPasswordDocumentListener);
	}
}
