package com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.admin.AdminAddUserPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.AddUserDocumentListener;
import com.rqueztech.ui.events.PasswordFieldListener;
import com.rqueztech.ui.events.TextFieldListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class AdminAddUserController {
	private AdminAddUserPanel adminAddUserPanel;
	private PanelCentral panelCentral;
	private TogglePasswordVisibility togglePasswordVisibility;
	
	public AdminAddUserController(AdminAddUserPanel adminAddUserPanel, PanelCentral panelCentral) {
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.adminAddUserPanel = adminAddUserPanel;
		this.panelCentral = panelCentral;
		
		this.invokeActionListeners();
        this.invokeDocumentListeners();
        this.enablePasswordTogglers();
	}
	
	// --------------------------------------------------------------------------------------
	public void invokeActionListeners() {
		this.userViewButtonListener();
        this.cancelButtonActionListener();
	}
	
	
	// --------------------------------------------------------------------------------------
	public void invokeDocumentListeners() {
		this.firstNameListener();
		this.lastNameListener();
		this.passphraseNameListener();
		this.addUserButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void userViewButtonListener() {
		JButton userViewButton = (JButton) this.adminAddUserPanel.components().get("ADD_USER_BUTTON_KEY");
		
		userViewButton.addActionListener(e -> {
			this.adminAddUserPanel.setVisible(false);
			this.resetFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void resetFields() {
		for(Component component : this.adminAddUserPanel.components().values()) {
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
	public void cancelButtonActionListener() {
		JButton adminLogin = (JButton) this.adminAddUserPanel.components().get("CANCEL_BUTTON_KEY");
		
		adminLogin.addActionListener(e -> {
			this.adminAddUserPanel.setVisible(false);
			this.resetFields();
			
			// Take the user back to the Admin User View Panel
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void lastNameListener() {
		JTextField lastName = (JTextField) this.adminAddUserPanel.components().get("LASTNAME_TEXTFIELD_KEY");
		
		// Listener for the last name field
		TextFieldListener lastNameFieldListener = 
				new TextFieldListener(lastName);
		
		lastName.getDocument().addDocumentListener(lastNameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void addUserButtonListener() {
		JTextField firstName = (JTextField) this.adminAddUserPanel.components().get("FIRSTNAME_TEXTFIELD_KEY");
		JTextField lastName = (JTextField) this.adminAddUserPanel.components().get("LASTNAME_TEXTFIELD_KEY");
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.components().get("PASSPHRASE_TEXTFIELD_KEY");
		
		JButton addUserButton = (JButton) this.adminAddUserPanel.components().get("ADD_USER_BUTTON_KEY");
		
		AddUserDocumentListener addUserDocumentListener = 
				new AddUserDocumentListener(addUserButton, firstName, lastName,
						passphrase, this.adminAddUserPanel.getGender());
		
		firstName.getDocument().addDocumentListener(addUserDocumentListener);
		lastName.getDocument().addDocumentListener(addUserDocumentListener);
		passphrase.getDocument().addDocumentListener(addUserDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void firstNameListener() {
		JTextField firstName = (JTextField) this.adminAddUserPanel.components().get("FIRSTNAME_TEXTFIELD_KEY");
		
		// Create a listener for the first name field
		TextFieldListener nameFieldListener = 
				new TextFieldListener(firstName);
		
		firstName.getDocument().addDocumentListener(nameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void passphraseNameListener() {
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.components().get("PASSPHRASE_TEXTFIELD_KEY");
		// Listener for the last name field
		PasswordFieldListener passwordFieldListener = 
				new PasswordFieldListener(passphrase);

		passphrase.getDocument().addDocumentListener(passwordFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.adminAddUserPanel.components().get("PASSPHRASE_VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.adminAddUserPanel.components().get("PASSPHRASE_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
}
