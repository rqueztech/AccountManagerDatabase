package com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.admin.AdminAddUserPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.AddUserDocumentListener;
import com.rqueztech.ui.events.PasswordFieldListener;
import com.rqueztech.ui.events.TextFieldListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class AdminAddUserController {
	private AdminAddUserPanel adminAddUserPanel;
	private TogglePasswordVisibility togglePasswordVisibility;
	
	public AdminAddUserController(AdminAddUserPanel adminAddUserPanel) {
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.adminAddUserPanel = adminAddUserPanel;
		
		this.invokeActionListeners();
        this.invokeDocumentListeners();
        this.enablePasswordTogglers();
	}
	
	// --------------------------------------------------------------------------------------
	private void invokeActionListeners() {
		this.userViewButtonListener();
        this.cancelButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void invokeDocumentListeners() {
		this.firstNameListener();
		this.lastNameListener();
		this.passphraseNameListener();
		this.addUserButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void userViewButtonListener() {
		JButton userViewButton = (JButton) this.adminAddUserPanel.getComponentsMap().get("ADD_USER_BUTTON_KEY");
		
		userViewButton.addActionListener(e -> {
			this.adminAddUserPanel.setVisible(false);
			this.resetFields();
			this.adminAddUserPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void resetFields() {
		for(Component component : this.adminAddUserPanel.getComponentsMap().values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
		
		this.adminAddUserPanel.getGender().setSelectedIndex(0);
	}
	
	// --------------------------------------------------------------------------------------
	private void cancelButtonActionListener() {
		JButton adminLogin = (JButton) this.adminAddUserPanel.getComponentsMap().get("CANCEL_BUTTON_KEY");
		
		adminLogin.addActionListener(e -> {
			this.adminAddUserPanel.setVisible(false);
			this.resetFields();
			
			// Take the user back to the Admin User View Panel
			this.adminAddUserPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void lastNameListener() {
		JTextField lastName = (JTextField) this.adminAddUserPanel.getComponentsMap().get("LASTNAME_TEXTFIELD_KEY");
		
		// Listener for the last name field
		TextFieldListener lastNameFieldListener = 
				new TextFieldListener(lastName);
		
		lastName.getDocument().addDocumentListener(lastNameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void addUserButtonListener() {
		JTextField firstName = (JTextField) this.adminAddUserPanel.getComponentsMap().get("FIRSTNAME_TEXTFIELD_KEY");
		JTextField lastName = (JTextField) this.adminAddUserPanel.getComponentsMap().get("LASTNAME_TEXTFIELD_KEY");
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
		
		JButton addUserButton = (JButton) this.adminAddUserPanel.getComponentsMap().get("ADD_USER_BUTTON_KEY");
		
		AddUserDocumentListener addUserDocumentListener = 
				new AddUserDocumentListener(addUserButton, firstName, lastName,
						passphrase, this.adminAddUserPanel.getGender());
		
		firstName.getDocument().addDocumentListener(addUserDocumentListener);
		lastName.getDocument().addDocumentListener(addUserDocumentListener);
		passphrase.getDocument().addDocumentListener(addUserDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void firstNameListener() {
		JTextField firstName = (JTextField) this.adminAddUserPanel.getComponentsMap().get("FIRSTNAME_TEXTFIELD_KEY");
		
		// Create a listener for the first name field
		TextFieldListener nameFieldListener = 
				new TextFieldListener(firstName);
		
		firstName.getDocument().addDocumentListener(nameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void passphraseNameListener() {
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
		// Listener for the last name field
		PasswordFieldListener passwordFieldListener = 
				new PasswordFieldListener(passphrase);

		passphrase.getDocument().addDocumentListener(passwordFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	private void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.adminAddUserPanel.getComponentsMap().get("PASSPHRASE_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get("PASSPHRASE_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
}
